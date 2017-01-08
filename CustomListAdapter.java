package com.example.pavan.tryapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CustomListAdapter extends ArrayAdapter<Legislators>
implements Filterable{

    ArrayList<Legislators> leg;
    Context context;
    int resource;
    public CustomListAdapter(Context context, int resource, ArrayList<Legislators> leg) {
        super(context, resource, leg);
        this.leg = leg;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.custom_list_layout,null,true);

        }
        Legislators legislators = getItem(position);
        ImageView imageView = (ImageView) row.findViewById(R.id.imageViewL);
        Picasso.with(context).load(legislators.getImage()).into(imageView);
     //   Log.d("test", "" + legislators.getImage());

        String FirstLine = legislators.getLname()+", "+legislators.getFname();
        TextView txtF = (TextView) row.findViewById(R.id.FirstLineId);
        txtF.setText(FirstLine);

        String SecondLine = "("+legislators.getParty()+")"+legislators.getStateName()+" - District "+legislators.getDistrict();
        TextView txtS = (TextView) row.findViewById(R.id.SecondLineId);
        txtS.setText(SecondLine);

        return row;
    }
}
