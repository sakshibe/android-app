package com.example.pavan.tryapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
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
 * {@link committees1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link committees1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class committees1 extends Fragment {

    ArrayList<Committees> arrayList;
    ArrayList<Committees> arrayListToSend = new ArrayList<Committees>();
    ArrayList<Committees> arrayListFilterHouse = new ArrayList<Committees>();
    ArrayList<Committees> arrayListFilterSenate = new ArrayList<Committees>();
    ArrayList<Committees> arrayListFilterJoint = new ArrayList<Committees>();

    ListView lv;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public committees1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment committees1.
     */
    // TODO: Rename and change types and number of parameters
    public static committees1 newInstance(String param1, String param2) {
        committees1 fragment = new committees1();
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
                new committees1.ReadJSON().execute("http://104.198.0.197:8080/committees?apikey=6a6f265b19cb46bdb4c2d463d1efb0f4&per_page=all");
            }
        });

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try{
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for(int i =0;i<jsonArray.length();i++)
                {
                    String bkpphn = "",bkpofc = "",bkppci="";
                    JSONObject comObject = jsonArray.getJSONObject(i);
                    if(comObject.has("phone"))
                    {
                        bkpphn = comObject.getString("phone");
                    }
                    else
                    {
                        bkpphn = "N.a";
                    }
                    if(comObject.has("office"))
                    {
                        bkpofc = comObject.getString("office");
                    }
                    else
                    {
                        bkpofc = "N.a";
                    }
                    if(comObject.has("parent_committee_id"))
                    {
                        bkppci = comObject.getString("parent_committee_id");
                    }
                    else
                    {
                        bkppci = "N.a";
                    }
                    arrayList.add(new Committees(
                            comObject.getString("committee_id"),
                            comObject.getString("name"),
                            comObject.getString("chamber"),
                            bkppci,
                            bkpphn,
                            bkpofc
                    ));
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            Collections.sort(arrayList, new Comparator<Committees>() {
                @Override
                public int compare(Committees o1, Committees o2) {
                    String st1 = ((Committees) o1).getComNm();
                    String st2 = ((Committees) o2).getComNm();
                    //ascending order
                    return st1.compareTo(st2);
                }
            });

            for(int i =0 ;i<arrayList.size();i++)
            {
                if(arrayList.get(i).getChamber().toString().equals("house"))
                {
                    arrayListFilterHouse.add(arrayList.get(i));
                }
                else if(arrayList.get(i).getChamber().toString().equals("senate"))
                {
                    arrayListFilterSenate.add(arrayList.get(i));
                }
                else
                {
                    arrayListFilterJoint.add(arrayList.get(i));
                }
            }
            final CustomCommListAdapter adapter = new CustomCommListAdapter(
                    getActivity(), R.layout.custom_comm_list_layout,arrayListFilterHouse
            );

            lv.setAdapter(adapter);
            arrayListToSend = arrayListFilterHouse;
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent nextActivity=new Intent(getActivity(),commViewActivity.class);
                    nextActivity.putExtra("data1",arrayListToSend);
                    nextActivity.putExtra("data2",position);
                    startActivity(nextActivity);
                }
            });

            TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabsC);
            Log.e("msg", String.valueOf(tabLayout));
            tabLayout.addTab(tabLayout.newTab().setText("HOUSE"));
            tabLayout.addTab(tabLayout.newTab().setText("SENATE"));
            tabLayout.addTab(tabLayout.newTab().setText("JOINT"));

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int p = tab.getPosition();
                    switch(p)
                    {
                        case 0:
                            arrayListToSend = arrayListFilterHouse;
                            lv.setAdapter(adapter);
                            break;
                        case 1:
                            arrayListToSend = arrayListFilterSenate;
                            CustomCommListAdapter adapter1 = new CustomCommListAdapter(
                                    getActivity(),R.layout.custom_comm_list_layout,arrayListFilterSenate);
                            lv.setAdapter(adapter1);
                            break;
                        case 2:
                            arrayListToSend = arrayListFilterJoint;
                            CustomCommListAdapter adapter2 = new CustomCommListAdapter(
                                    getActivity(),R.layout.custom_comm_list_layout,arrayListFilterJoint);
                            lv.setAdapter(adapter2);
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



//            super.onPostExecute(s);
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
        View view = inflater.inflate(R.layout.fragment_committees1, container, false);

        arrayList = new ArrayList<>();
        lv = (ListView) view.findViewById(R.id.listViewC);
        ((MainActivity) getActivity())
                .setActionBarTitle("Committees");
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
