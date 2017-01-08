package com.example.pavan.tryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class billViewDet extends AppCompatActivity {
    public static ArrayList<Bills> arrayListFavB = new ArrayList<Bills>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_view_det);



        final ArrayList<Bills> arrList = (ArrayList<Bills>) getIntent().getSerializableExtra("data1");
        final int position = getIntent().getIntExtra("data2",0);

        ImageView imgf = (ImageView) findViewById(R.id.starBF);
        imgf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayListFavB.add(arrList.get(position)) ;
            }
        });


        TextView tvb = (TextView) findViewById(R.id.billIDId);
        tvb.setText(arrList.get(position).getBill_id());

        TextView tvt = (TextView) findViewById(R.id.titleId);
        tvt.setText(arrList.get(position).getShort_title());

        TextView tvbt = (TextView) findViewById(R.id.typeId);
        tvbt.setText(arrList.get(position).getBill_type());

        TextView tvs = (TextView) findViewById(R.id.spId);
        tvs.setText(arrList.get(position).getSponsor());

        TextView tvc = (TextView) findViewById(R.id.chambBId);
        tvc.setText(arrList.get(position).getChamber());

        TextView tvst = (TextView) findViewById(R.id.stBId);
        tvst.setText(arrList.get(position).getStatus());

        TextView tvin = (TextView) findViewById(R.id.intId);
        tvin.setText(arrList.get(position).getIntroduced_on());

        TextView tvco = (TextView) findViewById(R.id.conId);
        tvco.setText(arrList.get(position).getCongressUrl());

        TextView tvv = (TextView) findViewById(R.id.verId);
        tvv.setText(arrList.get(position).getVersion_status());

        TextView tvbu = (TextView) findViewById(R.id.billUrlId);
        tvbu.setText(arrList.get(position).getBill_url());
    }
}
