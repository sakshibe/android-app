package com.example.pavan.tryapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class legislatorViewDet extends AppCompatActivity {
Context context;
    public static ArrayList<Legislators> arrayListFavL = new ArrayList<Legislators>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislator_view_det);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ArrayList<Legislators> arrList = (ArrayList<Legislators>) getIntent().getSerializableExtra("data1");
        final int position = getIntent().getIntExtra("data2",0);



        ImageView imageView = (ImageView) findViewById(R.id.imageViewDetL);
        Picasso.with(context).load(arrList.get(position).getImage()).into(imageView);

        String fbLink = "";
        if(arrList.get(position).getFb().equals("N.a"))
        {
            fbLink = "";
        }
        else
        {
            fbLink = "http://www.facebook.com/"+arrList.get(position).getFb();
        }

        ImageView img = (ImageView) findViewById(R.id.fb);
        final String finalFbLink = fbLink;
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!finalFbLink.equals(""))
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(finalFbLink)));
            }
        });

        ImageView imgf = (ImageView) findViewById(R.id.starLF);
        imgf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            arrayListFavL.add(arrList.get(position)) ;
                Log.e("msg",arrayListFavL.get(0).getParty());
            }
        });


        String twLink = "";
        if(arrList.get(position).getTwt().equals("N.a"))
        {
            twLink = "";
        }
        else
        {
            twLink = "http://www.twitter.com/"+arrList.get(position).getTwt();
        }
        ImageView imgt =(ImageView) findViewById(R.id.tw);
        final String finaltwLink = twLink;
        imgt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!finaltwLink.equals(""))
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(finaltwLink)));
            }
        });

        String wbLink = "";
        if(arrList.get(position).getWebsite().equals("N.a"))
        {
            wbLink = "";
        }
        else
        {
            wbLink = arrList.get(position).getWebsite();
        }
        ImageView imgw = (ImageView) findViewById(R.id.wb);
        final String finalwbLink = wbLink;
        imgw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!finalwbLink.equals(""))
                    Log.e("msg",finalwbLink);
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(finalwbLink)));
            }
        });
        String py = "";
        if(arrList.get(position).getParty().equals("R"))
        {
            py = "Republican";
        }
        if(arrList.get(position).getParty().equals("D"))
        {
            py = "Democratic";
        }
        TextView tp = (TextView) findViewById(R.id.partId);
        tp.setText(py);

        TextView tvn = (TextView) findViewById(R.id.nameId);
        tvn.setText(arrList.get(position).getTitle()+". "+arrList.get(position).getLname()+", "+arrList.get(position).getFname());

        TextView tve = (TextView) findViewById(R.id.emailId);
        tve.setText(arrList.get(position).getEmail());

        TextView tvc = (TextView) findViewById(R.id.chambId);
        tvc.setText(arrList.get(position).getChamber());

        TextView tvct = (TextView) findViewById(R.id.contactId);
        tvct.setText(arrList.get(position).getPhone());

        TextView tvs = (TextView) findViewById(R.id.startId);
        tvs.setText(arrList.get(position).getStartT());

        TextView tvend = (TextView) findViewById(R.id.endId);
        tvend.setText(arrList.get(position).getEndT());

        TextView tvterm = (TextView) findViewById(R.id.termId);
        tvterm.setText(arrList.get(position).getEndT());

        TextView tvofc = (TextView) findViewById(R.id.ofcId);
        tvofc.setText(arrList.get(position).getOffice());

        TextView tvst = (TextView) findViewById(R.id.stId);
        tvst.setText(arrList.get(position).getState());

        TextView tvfx = (TextView) findViewById(R.id.faxId);
        tvfx.setText(arrList.get(position).getFax());

        TextView tvb = (TextView) findViewById(R.id.bdayId);
        tvb.setText(arrList.get(position).getBirthday());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
