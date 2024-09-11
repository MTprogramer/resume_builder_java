package com.resumemaker.resumecvbuilder;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
//import com.github.barteksc.pdfviewer.PDFView;
import com.resumemaker.resumecvbuilder.ads.AdsCommon;

import java.io.File;

public class CvViewer extends AppCompatActivity {
    ImageView iv;
    Integer pageNumber = 0;
//    PDFView pdfView;

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_cv_viewer);


        //Reguler Banner Ads
        RelativeLayout admob_banner = (RelativeLayout) findViewById(R.id.Admob_Banner_Frame);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        FrameLayout qureka = (FrameLayout) findViewById(R.id.qureka);
        AdsCommon.RegulerBanner(this, admob_banner, adContainer, qureka);


        String stringExtra = getIntent().getStringExtra("cvpath");
        Log.d("ggggg", "filedata" + stringExtra);
//        this.pdfView = (PDFView) findViewById(R.id.pdfView1);
        File file = new File(stringExtra);
        try {
            Log.d("ffff", "fff" + stringExtra);
//            this.pdfView.fromFile(file).swipeHorizontal(false).defaultPage(this.pageNumber.intValue()).enableSwipe(true).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
