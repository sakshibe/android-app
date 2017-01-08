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
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.pavan.tryapp.billViewDet.arrayListFavB;
import static com.example.pavan.tryapp.commViewActivity.arrayListFavC;
import static com.example.pavan.tryapp.legislatorViewDet.arrayListFavL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavFragment extends Fragment {
    ArrayList<Legislators> arrayList;
    ListView lv;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FavFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavFragment newInstance(String param1, String param2) {
        FavFragment fragment = new FavFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav, container, false);

    //    if(tabLayout1 != null)
    //    tabLayout1.addTab(tabLayout1.newTab().setText("Hi"));
    //    tabLayout.addTab(tabLayout.newTab().setText("LEGISLATORS"));
//        tabLayout.addTab(tabLayout.newTab().setText("BILLS"));
//        tabLayout.addTab(tabLayout.newTab().setText("COMMITTEES"));

        arrayList = new ArrayList<>();
        lv = (ListView) view.findViewById(R.id.listViewF);
        ((MainActivity) getActivity())
                .setActionBarTitle("Favorites");
        final CustomListAdapter adapter = new CustomListAdapter(
                getActivity(),R.layout.custom_list_layout,arrayListFavL
        );
        lv.setAdapter(adapter);

        TabLayout tabLayout1 = (TabLayout) view.findViewById(R.id.tabsPos);

        tabLayout1.addTab(tabLayout1.newTab().setText("LEGISLATORS"));
       tabLayout1.addTab(tabLayout1.newTab().setText("BILLS"));
        tabLayout1.addTab(tabLayout1.newTab().setText("COMMITTEES"));
        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int p = tab.getPosition();
                switch(p)
                {
                    case 0:
                        final CustomListAdapter adapter = new CustomListAdapter(
                                getActivity(),R.layout.custom_list_layout,arrayListFavL
                        );
                        lv.setAdapter(adapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent nextActivity=new Intent(getActivity(),legislatorViewDet.class);
                                nextActivity.putExtra("data1",arrayListFavL);
                                nextActivity.putExtra("data2",position);
                                startActivity(nextActivity);
                            }
                        });



                        break;
                    case 1:
                        final CustomBillListAdapter adapter1 = new CustomBillListAdapter(
                                getActivity(),R.layout.custom_bill_list_layout,arrayListFavB
                        );
                        lv.setAdapter(adapter1);
                        break;
                    case 2:
                        final CustomCommListAdapter adapter2 = new CustomCommListAdapter(
                                getActivity(),R.layout.custom_comm_list_layout,arrayListFavC
                        );
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
