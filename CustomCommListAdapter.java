package com.example.pavan.tryapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;



public class CustomCommListAdapter extends ArrayAdapter<Committees>
implements Filterable{

    ArrayList<Committees> com;
    Context context;
    int resource;
    public CustomCommListAdapter(Context context, int resource, ArrayList<Committees> com) {
        super(context, resource, com);
        this.com = com;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.custom_comm_list_layout,null,true);

        }
        Committees committees = getItem(position);

        TextView txtc = (TextView) row.findViewById(R.id.FirstLineCId);
        txtc.setText(committees.getComId());

        TextView txtn = (TextView) row.findViewById(R.id.SecondLineCId);
        txtn.setText(committees.getComNm());

        TextView txtch = (TextView) row.findViewById(R.id.ThirdLineCId);
        txtch.setText(committees.getChamber());

        return row;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
