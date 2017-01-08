package com.example.pavan.tryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class commViewActivity extends AppCompatActivity {
    public static ArrayList<Committees> arrayListFavC = new ArrayList<Committees>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_view);

        final ArrayList<Committees> arrList = (ArrayList<Committees>) getIntent().getSerializableExtra("data1");
        final int position = getIntent().getIntExtra("data2",0);

        ImageView imgf = (ImageView) findViewById(R.id.starCF);
        imgf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayListFavC.add(arrList.get(position)) ;
            }
        });


        TextView tvc = (TextView) findViewById(R.id.comIDId);
        tvc.setText(arrList.get(position).getComId());

        TextView tvc1 = (TextView) findViewById(R.id.nmId);
        tvc1.setText(arrList.get(position).getComNm());

        TextView tvc2 = (TextView) findViewById(R.id.chmId);
        tvc2.setText(arrList.get(position).getChamber());

        TextView tvc3 = (TextView) findViewById(R.id.parId);
        tvc3.setText(arrList.get(position).getParCom());

        TextView tvc4 = (TextView) findViewById(R.id.conId);
        tvc4.setText(arrList.get(position).getCnct());

        TextView tvc5 = (TextView) findViewById(R.id.ofcId);
        tvc5.setText(arrList.get(position).getOfc());


    }
}
