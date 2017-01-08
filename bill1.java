package com.example.pavan.tryapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link bill1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link bill1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bill1 extends Fragment {

    ArrayList<Bills> arrayList;
    ArrayList<Bills> arrayListActive = new ArrayList<Bills>();
    ArrayList<Bills> arrayListNew = new ArrayList<Bills>();
    ArrayList<Bills> arrayListToSend = new ArrayList<Bills>();
    ListView lv;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public bill1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bill1.
     */
    // TODO: Rename and change types and number of parameters
    public static bill1 newInstance(String param1, String param2) {
        bill1 fragment = new bill1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //new legislator1.ReadJSON().execute("http://webappenv-env.us-west-2.elasticbeanstalk.com/");
                new bill1.ReadJSON().execute("http://104.198.0.197:8080/bills?last_version.urls.pdf__exists=true&apikey=6a6f265b19cb46bdb4c2d463d1efb0f4&per_page=50");
            }
        });

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    class ReadJSON extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }
        @Override
        protected void onPostExecute(String content) {
            try{
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for(int i = 0;i<jsonArray.length();i++)
                {
                    String bkplt;
                    JSONObject bilObject = jsonArray.getJSONObject(i);
                    if(bilObject.getString("short_title") == null)
                        bkplt = bilObject.getString("official_title");
                    else
                     bkplt = bilObject.getString("short_title");
                    arrayList.add(new Bills(
                            bilObject.getString("bill_id"),
                            bilObject.getString("bill_type"),
                            bilObject.getString("official_title"),
                            bilObject.getString("introduced_on"),
                            bilObject.getJSONObject("sponsor").getString("first_name"),
                            bilObject.getString("chamber"),
                            bilObject.getJSONObject("history").getString("active"),
                            bilObject.getJSONObject("urls").getString("congress"),
                            bilObject.getJSONObject("last_version").getString("version_name"),
                           bilObject.getJSONObject("last_version").getJSONObject("urls").getString("pdf")
                    ));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Collections.sort(arrayList, new Comparator<Bills>() {
                @Override
                public int compare(Bills o1, Bills o2) {
                    String st1 = ((Bills) o1).getIntroduced_on();
                    String st2 = ((Bills) o2).getIntroduced_on();
                    //ascending order
                    return st1.compareTo(st2);
                }
            });
            arrayListToSend = arrayList;
            for(int i =0 ;i<arrayList.size();i++)
            {
                if(arrayList.get(i).getStatus().toString().equals("true"))
                {
                    arrayListActive.add(arrayList.get(i));
                }
                else
                {
                    arrayListNew.add(arrayList.get(i));
                }
            }

            final CustomBillListAdapter adapter = new CustomBillListAdapter(
                    getActivity(),R.layout.custom_bill_list_layout,arrayListActive
            );
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent nextActivity=new Intent(getActivity(),billViewDet.class);
                    nextActivity.putExtra("data1",arrayListToSend);
                    nextActivity.putExtra("data2",position);
                    startActivity(nextActivity);
                }
            });

            TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabsB);
            tabLayout.addTab(tabLayout.newTab().setText("Active Bills"));
            tabLayout.addTab(tabLayout.newTab().setText("New Bills"));

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int p = tab.getPosition();
                    switch(p)
                    {
                        case 0:
                            arrayListToSend = arrayListActive;
                            lv.setAdapter(adapter);
                            break;
                        case 1:
                            arrayListToSend = arrayListNew;
                            CustomBillListAdapter adapter1 = new CustomBillListAdapter(
                                    getActivity(),R.layout.custom_bill_list_layout,arrayListNew);
                            lv.setAdapter(adapter1);
                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            // super.onPostExecute(s);
        }
    }
    private static String readURL(String theUrl){
        StringBuilder content = new StringBuilder();
        try{
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line = bufferedReader.readLine()) !=null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return content.toString();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill1, container, false);
        arrayList = new ArrayList<>();
        lv = (ListView) view.findViewById(R.id.listViewB);
        ((MainActivity) getActivity())
                .setActionBarTitle("Bills");
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
