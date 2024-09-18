package com.resumemaker.resumecvbuilder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.tabs.TabLayout;
import com.resumemaker.resumecvbuilder.ads.AdsCommon;

import java.util.Iterator;

public class Create_CV extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 200;
    public static ImageView imgSave = null;
    public static boolean isFilled = false;
    public static ImageView nextTabImg;
    public static CustomViewPager viewPager;
    SharedPreferences.Editor editor;
    int iNewPosition;
    
    ObjectCallback objectCallback;
    Objective_Fragment objective_fragment;
    MyCallback personalCallback;
    SharedPreferences settings;
    TabLayout tabLayout;
    TemplateCallback templateCallback;
    UtilSharedPreferences utilSharedPreferences;
    ViewPagerAdapter viewPagerAdapter;

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_create_cv);



        //Reguler Banner Ads
        RelativeLayout admob_banner = (RelativeLayout) findViewById(R.id.Admob_Banner_Frame);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        FrameLayout qureka = (FrameLayout) findViewById(R.id.qureka);
        AdsCommon.RegulerBanner(this, admob_banner, adContainer, qureka);



        this.utilSharedPreferences = new UtilSharedPreferences(getApplicationContext());
        if (!checkPermission()) {
            requestPermission();
        }
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS_NAME", 0);
        this.settings = sharedPreferences;
        this.editor = sharedPreferences.edit();
        nextTabImg = (ImageView) findViewById(R.id.img_next1);
        imgSave = (ImageView) findViewById(R.id.img_save);
        this.objective_fragment = new Objective_Fragment();
        CustomViewPager customViewPager = (CustomViewPager) findViewById(R.id.myDialogViewPager);
        viewPager = customViewPager;
        customViewPager.setSwipeEnabled(false);
        ViewPagerAdapter viewPagerAdapter2 = new ViewPagerAdapter(getSupportFragmentManager());
        this.viewPagerAdapter = viewPagerAdapter2;
        viewPager.setAdapter(viewPagerAdapter2);
        this.tabLayout = (TabLayout) findViewById(R.id.tablayout1);
        imgSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Create_CV.this.templateCallback.tempcallback(true);
            }
        });
        nextTabImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    Create_CV create_CV = Create_CV.this;
                    create_CV.getInfo(create_CV.iNewPosition);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("tabselect", "onClick: " + e.getMessage());
                }
            }
        });
        this.tabLayout.setupWithViewPager(viewPager);
        this.tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {
            public void onTabReselected(TabLayout.Tab tab) {
            }

            public void onTabUnselected(TabLayout.Tab tab) {
            }

            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    Create_CV.nextTabImg.setVisibility(0);
                    Create_CV.imgSave.setVisibility(8);
                    Create_CV.nextTabImg.setImageResource(R.drawable.next_btn);
                } else if (position == 1) {
                    Create_CV.nextTabImg.setVisibility(0);
                    Create_CV.imgSave.setVisibility(8);
                    Create_CV.nextTabImg.setImageResource(R.drawable.next_btn);
                } else if (position == 2) {
                    Create_CV.nextTabImg.setVisibility(0);
                    Create_CV.imgSave.setVisibility(8);
                    Create_CV.nextTabImg.setImageResource(R.drawable.next_btn);
                } else if (position == 3) {
                    Create_CV.nextTabImg.setVisibility(0);
                    Create_CV.imgSave.setVisibility(8);
                    Create_CV.nextTabImg.setImageResource(R.drawable.next_btn);
                } else if (position == 4) {
                    Create_CV.nextTabImg.setVisibility(0);
                    Create_CV.imgSave.setVisibility(8);
                    Create_CV.nextTabImg.setImageResource(R.drawable.next_btn);
                } else if (position == 5) {
                    Create_CV.nextTabImg.setVisibility(0);
                    Create_CV.imgSave.setVisibility(8);
                    Create_CV.nextTabImg.setImageResource(R.drawable.next_btn);
                } else if (position == 6) {
                    Create_CV.imgSave.setVisibility(0);
                    Create_CV.nextTabImg.setVisibility(8);
                    Create_CV.nextTabImg.setImageResource(R.drawable.savebtn);
                }
                Log.d("mytab", "tab" + Create_CV.this.iNewPosition);
            }
        });
    }

    public void getInfo(int i) {
        try {
            int selectedTabPosition = this.tabLayout.getSelectedTabPosition() + 1;
            if (selectedTabPosition == 1) {
                this.personalCallback.callbackCall(true);
            }
            if (selectedTabPosition == 2) {
                this.objectCallback.callback(true);
            }
            if (selectedTabPosition == 6) {
                nextTabImg.setVisibility(8);
                imgSave.setVisibility(0);
                nextTabImg.setImageResource(R.drawable.savebtn);
            }
            if (isFilled) {
                //
                this.tabLayout.getTabAt(selectedTabPosition).select();
                return;
            }
            Toast.makeText(this, "Please fill all fields", 0).show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("tabselect", "onClick: " + e.getMessage());
        }
    }

    private void setUntouchableTab() {
        this.tabLayout.setupWithViewPager(viewPager, true);
        this.tabLayout.clearOnTabSelectedListeners();
        Iterator it = this.tabLayout.getTouchables().iterator();
        while (it.hasNext()) {
            ((View) it.next()).setEnabled(false);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Uri data = intent.getData();
        Log.d("uripic", "check your pics" + data);
        try {
            PersonalInfo_Fragment.imgcircularimage.setImageURI(data);
            PersonalInfo_Fragment.checkImage = true;
            this.editor.putString("imageURI", data.toString());
            this.editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void passMsgToTextFrag(MyCallback myCallback) {
        this.personalCallback = myCallback;
    }

    public void passMsgToObjectFrag(ObjectCallback objectCallback2) {
        this.objectCallback = objectCallback2;
    }

    public void passMsgToTempletFrag(TemplateCallback templateCallback2) {
        this.templateCallback = templateCallback2;
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            Toast.makeText(this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", 1).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 200);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 200) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                Log.e("value", "Permission Denied, You cannot use local drive .");
            } else {
                Log.e("value", "Permission Granted, Now you can use local drive .");
            }
        }
    }
}
