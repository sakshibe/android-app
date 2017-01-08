package com.example.pavan.tryapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
 * {@link legislator1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link legislator1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class legislator1 extends Fragment {

    ArrayList<Legislators> arrayList;
    ArrayList<Legislators> arrayListFilterHouse = new ArrayList<Legislators>();
    ArrayList<Legislators> arrayListFilterSenate = new ArrayList<Legislators>();
    ArrayList<Legislators> arrayListToSend = new ArrayList<Legislators>();
    ListView lv;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public legislator1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment legislator1.
     */
    // TODO: Rename and change types and number of parameters
    public static legislator1 newInstance(String param1, String param2) {
        legislator1 fragment = new legislator1();
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
                new legislator1.ReadJSON().execute("http://104.198.0.197:8080/legislators?apikey=6a6f265b19cb46bdb4c2d463d1efb0f4&per_page=all");
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
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
              //  JSONArray p = new JSONArray(content);
               // String s = (String) p.get(0);
                //JSONObject jsonObject = new JSONObject(s);
                //JSONArray jsonArray = jsonObject.getJSONArray("results");
                String bkpFb, bkpTw, bkpWb;
                for(int i = 0;i<jsonArray.length();i++)
                {
                    JSONObject legObject = jsonArray.getJSONObject(i);
                    if (legObject.has("facebook_id")) {
                        bkpFb = legObject.getString("facebook_id");
                    } else {
                       bkpFb = "N.a";
                    }
                    if(legObject.has("twitter_id")){
                        bkpTw = legObject.getString("twitter_id");
                    }else{
                        bkpTw = "N.a";
                    }
                    if(legObject.has("website")){
                        bkpWb = legObject.getString("website");
                    }else{
                        bkpWb = "N.a";
                    }
                    arrayList.add(new Legislators(
                            legObject.getString("title"),
                            legObject.getString("oc_email"),
                            legObject.getString("chamber"),
                            legObject.getString("phone"),
                            legObject.getString("term_start"),
                            legObject.getString("term_end"),
                            legObject.getString("office"),
                            legObject.getString("state"),
                            legObject.getString("fax"),
                            legObject.getString("birthday"),
                            legObject.getString("first_name"),
                            legObject.getString("last_name"),
                            legObject.getString("party"),
                            legObject.getString("state_name"),
                            legObject.getString("district"),
                            legObject.getString("bioguide_id"),
                            bkpFb,
                            bkpTw,
                            bkpWb
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Collections.sort(arrayList, new Comparator<Legislators>() {
                @Override
                public int compare(Legislators o1, Legislators o2) {
                    String st1 = ((Legislators) o1).getStateName();
                    String st2 = ((Legislators) o2).getStateName();
                    //ascending order
                    return st1.compareTo(st2);
                }
            });
            final CustomListAdapter adapter = new CustomListAdapter(
                    getActivity(),R.layout.custom_list_layout,arrayList
            );
            lv.setAdapter(adapter);

            for(int i =0 ;i<arrayList.size();i++)
            {
                if(arrayList.get(i).getChamber().toString().equals("house"))
                {
                    arrayListFilterHouse.add(arrayList.get(i));
                }
                else
                {
                    arrayListFilterSenate.add(arrayList.get(i));
                }
            }
            Collections.sort(arrayListFilterHouse, new Comparator<Legislators>() {
                @Override
                public int compare(Legislators o1, Legislators o2) {
                    String st1 = ((Legislators) o1).getLname();
                    String st2 = ((Legislators) o2).getLname();
                    //ascending order
                    return st1.compareTo(st2);
                }
            });
            Collections.sort(arrayListFilterSenate, new Comparator<Legislators>() {
                @Override
                public int compare(Legislators o1, Legislators o2) {
                    String st1 = ((Legislators) o1).getLname();
                    String st2 = ((Legislators) o2).getLname();
                    //ascending order
                    return st1.compareTo(st2);
                }
            });

            arrayListToSend = arrayList;
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent nextActivity=new Intent(getActivity(),legislatorViewDet.class);
                    nextActivity.putExtra("data1",arrayListToSend);
                    nextActivity.putExtra("data2",position);
                    startActivity(nextActivity);
                }
            });

            TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabs);
            tabLayout.addTab(tabLayout.newTab().setText("BY STATES"));
            tabLayout.addTab(tabLayout.newTab().setText("HOUSE"));
            tabLayout.addTab(tabLayout.newTab().setText("SENATE"));

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int p = tab.getPosition();
                    switch(p)
                    {
                        case 0:
                            arrayListToSend = arrayList;
                            lv.setAdapter(adapter);
                            break;
                        case 1:
                            arrayListToSend = arrayListFilterHouse;
                            CustomListAdapter adapter1 = new CustomListAdapter(
                                    getActivity(),R.layout.custom_list_layout,arrayListFilterHouse);
                            lv.setAdapter(adapter1);
                            break;
                        case 2:
                            arrayListToSend = arrayListFilterSenate;
                            CustomListAdapter adapter2 = new CustomListAdapter(
                                    getActivity(),R.layout.custom_list_layout,arrayListFilterSenate);
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
        View view = inflater.inflate(R.layout.fragment_legislator1, container, false);

        arrayList = new ArrayList<>();
        lv = (ListView) view.findViewById(R.id.listViewL);
        ((MainActivity) getActivity())
                .setActionBarTitle("Legislators");
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
