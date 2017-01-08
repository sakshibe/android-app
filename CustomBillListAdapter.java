package com.example.pavan.tryapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class CustomBillListAdapter extends ArrayAdapter {
    ArrayList<Bills> bil;
    Context context;
    int resource;

    public CustomBillListAdapter(Context context, int resource, ArrayList<Bills> bil) {
        super(context, resource, bil);
        this.context = context;
        this.resource = resource;
        this.bil = bil;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.custom_bill_list_layout,null,true);

        }
        Bills bills = (Bills) getItem(position);
        TextView tvb = (TextView) row.findViewById(R.id.FirstLineBId);
        tvb.setText(bills.getBill_id());

        TextView tvs = (TextView) row.findViewById(R.id.SecondLineBId);
        tvs.setText(bills.getShort_title());

        TextView tvi = (TextView) row.findViewById(R.id.ThirdLineBId);
        tvi.setText(bills.getIntroduced_on());

return row;

        // return super.getView(position, convertView, parent);
    }
}
