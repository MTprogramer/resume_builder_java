package com.resumemaker.resumecvbuilder;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.ads.AdsCommon;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SaveActivity extends AppCompatActivity implements SaveCVCalback {

    ArrayList<File> arrylist;
    ImageView ivBackArrow;
    RecyclerView.LayoutManager layoutmanager;
    LinearLayout lyNotFileFound;
    File path;
    RecyclerView recyclerView;
    SaveCvAdapter saveCvAdapter;
    UtilSharedPreferences utilSharedPreferences;

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_save);

        //Reguler Banner Ads
        RelativeLayout admob_banner = (RelativeLayout) findViewById(R.id.Admob_Banner_Frame);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        FrameLayout qureka = (FrameLayout) findViewById(R.id.qureka);
        AdsCommon.RegulerBanner(this, admob_banner, adContainer, qureka);


        this.utilSharedPreferences = new UtilSharedPreferences(getApplicationContext());
        this.ivBackArrow = (ImageView) findViewById(R.id.img_resultArrows);
        String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/PdfCv/";
        Log.d("gggg", "pathdata" + str);
        File[] listFiles = new File(String.valueOf(str)).listFiles();
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recler_id);
        this.recyclerView = recyclerView2;
        recyclerView2.setHasFixedSize(true);
        this.layoutmanager = new LinearLayoutManager(this);
        ArrayList<File> arrayList = new ArrayList<>();
        this.arrylist = arrayList;
        if (listFiles != null) {
            arrayList.addAll(Arrays.asList(listFiles));
            Collections.reverse(this.arrylist);
            this.recyclerView.setLayoutManager(this.layoutmanager);
            SaveCvAdapter saveCvAdapter2 = new SaveCvAdapter(this, this.arrylist);
            this.saveCvAdapter = saveCvAdapter2;
            saveCvAdapter2.setOnItemClickListener(this);
            this.recyclerView.setVisibility(0);
            this.saveCvAdapter.notifyDataSetChanged();
            this.recyclerView.setAdapter(this.saveCvAdapter);
            Log.d("dataarray", "" + listFiles.length);
            this.lyNotFileFound = (LinearLayout) findViewById(R.id.lyfilenotfound);
        }
        if (this.arrylist.size() >= 3) {

        }
        if (this.arrylist.size() > 0) {
            this.lyNotFileFound.setVisibility(8);
        }
        this.ivBackArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveActivity.this.finish();
            }
        });
    }

    public void sendSaveList(ArrayList<File> arrayList) {
        if (arrayList.size() == 0) {
            this.lyNotFileFound.setVisibility(0);
        }
        if (arrayList.size() < 3) {

        }
    }
}
