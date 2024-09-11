package com.resumemaker.resumecvbuilder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.net.MailTo;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.resumemaker.resumecvbuilder.ads.AdsCommon;
import com.resumemaker.resumecvbuilder.ads.MyApplication;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    AppUpdateManager appUpdateManager;

    public Long MIN_CLICK_INTERVAL = 600L;
    ImageView createImg;
    Dialog dialog1;
    Dialog dialogExit;
    DrawerLayout drawerLayout;
    ImageView imgSaved;
    ImageView inNineTemp;
    ImageView ivEdit;
    ImageView ivEigthPmp;
    ImageView ivFifthtampl;
    ImageView ivFirsttmp;
    ImageView ivFourthTamplet;
    ImageView ivSecondTemplet;
    ImageView ivSeventhTmp;
    ImageView ivSixthTmp;
    ImageView ivThirdcvtmplet;
    ImageView ivTmpEleven;
    
    public Long mLastClickTime = 0L;
    Intent myintent;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    UtilSharedPreferences utilSharedPreferences;

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);


        //Reguler Banner Ads
        RelativeLayout admob_banner = (RelativeLayout) findViewById(R.id.Admob_Banner_Frame);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        FrameLayout qureka = (FrameLayout) findViewById(R.id.qureka);
        AdsCommon.RegulerBanner(this, admob_banner, adContainer, qureka);


        //InAppUpdate
        checkInAppUpdate();


        this.utilSharedPreferences = new UtilSharedPreferences(getApplicationContext());
        this.dialog1 = new Dialog(this, R.style.FullScreenDialog);
        this.ivFirsttmp = (ImageView) findViewById(R.id.ivtampletfirst);
        this.ivSecondTemplet = (ImageView) findViewById(R.id.ivsecondteplet);
        this.ivThirdcvtmplet = (ImageView) findViewById(R.id.ivtepletthree);
        this.ivFourthTamplet = (ImageView) findViewById(R.id.ivfourthtmplet);
        this.ivFifthtampl = (ImageView) findViewById(R.id.ivfithtamplet);
        this.ivSixthTmp = (ImageView) findViewById(R.id.ivsithtmpl);
        this.ivSeventhTmp = (ImageView) findViewById(R.id.ivsenthtmp);
        this.ivEigthPmp = (ImageView) findViewById(R.id.iveightamplet);
        this.ivTmpEleven = (ImageView) findViewById(R.id.cvsanja);
        this.inNineTemp = (ImageView) findViewById(R.id.ivninetemp);
        this.ivFirsttmp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity mainActivity = MainActivity.this;
                mainActivity.showdialogforTemp(mainActivity.getResources().getDrawable(R.drawable.maincv));
            }
        });
        this.ivSecondTemplet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Long valueOf = Long.valueOf(SystemClock.uptimeMillis());
                Long valueOf2 = Long.valueOf(valueOf.longValue() - MainActivity.this.mLastClickTime.longValue());
                Long unused = MainActivity.this.mLastClickTime = valueOf;
                if (valueOf2.longValue() > MainActivity.this.MIN_CLICK_INTERVAL.longValue()) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.showdialogforTemp(mainActivity.getResources().getDrawable(R.drawable.cvfirst));
                }
            }
        });
        this.ivThirdcvtmplet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Long valueOf = Long.valueOf(SystemClock.uptimeMillis());
                Long valueOf2 = Long.valueOf(valueOf.longValue() - MainActivity.this.mLastClickTime.longValue());
                Long unused = MainActivity.this.mLastClickTime = valueOf;
                if (valueOf2.longValue() > MainActivity.this.MIN_CLICK_INTERVAL.longValue()) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.showdialogforTemp(mainActivity.getResources().getDrawable(R.drawable.cvten));
                }
            }
        });
        this.ivFourthTamplet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Long valueOf = Long.valueOf(SystemClock.uptimeMillis());
                Long valueOf2 = Long.valueOf(valueOf.longValue() - MainActivity.this.mLastClickTime.longValue());
                Long unused = MainActivity.this.mLastClickTime = valueOf;
                if (valueOf2.longValue() > MainActivity.this.MIN_CLICK_INTERVAL.longValue()) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.showdialogforTemp(mainActivity.getResources().getDrawable(R.drawable.cvsecond));
                }
            }
        });
        this.ivFifthtampl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Long valueOf = Long.valueOf(SystemClock.uptimeMillis());
                Long valueOf2 = Long.valueOf(valueOf.longValue() - MainActivity.this.mLastClickTime.longValue());
                Long unused = MainActivity.this.mLastClickTime = valueOf;
                if (valueOf2.longValue() > MainActivity.this.MIN_CLICK_INTERVAL.longValue()) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.showdialogforTemp(mainActivity.getResources().getDrawable(R.drawable.cvthird));
                }
            }
        });
        this.ivSixthTmp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Long valueOf = Long.valueOf(SystemClock.uptimeMillis());
                Long valueOf2 = Long.valueOf(valueOf.longValue() - MainActivity.this.mLastClickTime.longValue());
                Long unused = MainActivity.this.mLastClickTime = valueOf;
                if (valueOf2.longValue() > MainActivity.this.MIN_CLICK_INTERVAL.longValue()) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.showdialogforTemp(mainActivity.getResources().getDrawable(R.drawable.cvfourth));
                }
            }
        });
        this.ivSeventhTmp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Long valueOf = Long.valueOf(SystemClock.uptimeMillis());
                Long valueOf2 = Long.valueOf(valueOf.longValue() - MainActivity.this.mLastClickTime.longValue());
                Long unused = MainActivity.this.mLastClickTime = valueOf;
                if (valueOf2.longValue() > MainActivity.this.MIN_CLICK_INTERVAL.longValue()) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.showdialogforTemp(mainActivity.getResources().getDrawable(R.drawable.cvsixth));
                }
            }
        });
        this.ivEigthPmp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Long valueOf = Long.valueOf(SystemClock.uptimeMillis());
                Long valueOf2 = Long.valueOf(valueOf.longValue() - MainActivity.this.mLastClickTime.longValue());
                Long unused = MainActivity.this.mLastClickTime = valueOf;
                if (valueOf2.longValue() > MainActivity.this.MIN_CLICK_INTERVAL.longValue()) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.showdialogforTemp(mainActivity.getResources().getDrawable(R.drawable.cvnine));
                }
            }
        });
        this.inNineTemp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Long valueOf = Long.valueOf(SystemClock.uptimeMillis());
                Long valueOf2 = Long.valueOf(valueOf.longValue() - MainActivity.this.mLastClickTime.longValue());
                Long unused = MainActivity.this.mLastClickTime = valueOf;
                if (valueOf2.longValue() > MainActivity.this.MIN_CLICK_INTERVAL.longValue()) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.showdialogforTemp(mainActivity.getResources().getDrawable(R.drawable.cvseven));
                }
            }
        });
        this.ivTmpEleven.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Long valueOf = Long.valueOf(SystemClock.uptimeMillis());
                Long valueOf2 = Long.valueOf(valueOf.longValue() - MainActivity.this.mLastClickTime.longValue());
                Long unused = MainActivity.this.mLastClickTime = valueOf;
                if (valueOf2.longValue() > MainActivity.this.MIN_CLICK_INTERVAL.longValue()) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.showdialogforTemp(mainActivity.getResources().getDrawable(R.drawable.sanjtamplet));
                }
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.id_Carated);
        this.createImg = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.myintent = new Intent(MainActivity.this, Create_CV.class);
                AdsCommon.InterstitialAd(MainActivity.this, myintent);

            }
        });
        ImageView imageView2 = (ImageView) findViewById(R.id.idSaved);
        this.imgSaved = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.myintent = new Intent(MainActivity.this, SaveActivity.class);
                AdsCommon.InterstitialAd(MainActivity.this, myintent);
            }
        });
        this.nav = (NavigationView) findViewById(R.id.navmenu);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, this.drawerLayout, (Toolbar) findViewById(R.id.toolbar1), R.string.open, R.string.close);
        this.toggle = actionBarDrawerToggle;
        this.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        this.toggle.syncState();
        this.nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_feedback /*2131297150*/:
                        Intent intent = new Intent("android.intent.action.SENDTO");
                        intent.setData(Uri.parse(MailTo.MAILTO_SCHEME + Uri.encode("abcdeveloper.net@gmail.com") + "?subject=" + Uri.encode("Feedback") + "$body=" + Uri.encode("")));
                        MainActivity.this.startActivity(Intent.createChooser(intent, "sent to"));
                        MainActivity.this.drawerLayout.close();
                        return true;
                    case R.id.nav_privacy /*2131297151*/:
                        //privacy
                        Intent intentPrivacy = new Intent(Intent.ACTION_VIEW, Uri.parse(MyApplication.PrivacyPolicy));
                        intentPrivacy.setPackage("com.android.chrome");
                        startActivity(intentPrivacy);
                        MainActivity.this.drawerLayout.close();
                        return true;
                    case R.id.nav_rateus /*2131297152*/:
                        rateUs();
                        MainActivity.this.drawerLayout.close();
                        return true;
                    case R.id.nav_share /*2131297153*/:
                        shareapp();
                        MainActivity.this.drawerLayout.closeDrawer((int) GravityCompat.START);
                        return true;
                    default:
                        return true;
                }
            }

            private DatePickerDialog createDialogWithoutDateField() {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this.getApplicationContext(), (DatePickerDialog.OnDateSetListener) null, 2014, 1, 24);
                try {
                    for (Field field : datePickerDialog.getClass().getDeclaredFields()) {
                        if (field.getName().equals("mDatePicker")) {
                            field.setAccessible(true);
                            DatePicker datePicker = (DatePicker) field.get(datePickerDialog);
                            for (Field field2 : field.getType().getDeclaredFields()) {
                                Log.i("test", field2.getName());
                                if ("mDaySpinner".equals(field2.getName())) {
                                    field2.setAccessible(true);
                                    ((View) field2.get(datePicker)).setVisibility(8);
                                }
                            }
                        }
                    }
                } catch (Exception unused) {
                }
                return datePickerDialog;
            }

            public void shareapp() {
                try {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.SUBJECT", "My application name");
                    intent.putExtra("android.intent.extra.TEXT", "\nLet me recommend you this application\n\n" + " https://play.google.com/store/apps/details?id=" + getPackageName());
                    MainActivity.this.startActivity(Intent.createChooser(intent, "choose one"));
                } catch (Exception unused) {
                }
            }

            private void rateUs() {
                String packageName = MainActivity.this.getPackageName();
                try {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
                } catch (ActivityNotFoundException unused) {
                    MainActivity mainActivity2 = MainActivity.this;
                    mainActivity2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
                }
            }
        });
    }

    
    public void showdialogforTemp(Drawable drawable) {
        this.dialog1.setContentView(R.layout.viewtamplets);
        ((ImageView) this.dialog1.findViewById(R.id.goProDialogImage)).setImageDrawable(drawable);
        this.dialog1.show();
    }

    @Override
    public void onBackPressed() {
        if (this.dialog1.isShowing()) {
            super.onBackPressed();
            this.dialog1.dismiss();
            return;
        }
        ExitDialog();
    }


    private void ExitDialog() {

        final Dialog dialog = new Dialog(MainActivity.this, R.style.DialogTheme);
        dialog.setContentView(R.layout.popup_exit_dialog);
        dialog.setCancelable(false);

        RelativeLayout no = (RelativeLayout) dialog.findViewById(R.id.no);
        RelativeLayout rate = (RelativeLayout) dialog.findViewById(R.id.rate);
        RelativeLayout yes = (RelativeLayout) dialog.findViewById(R.id.yes);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String rateapp = getPackageName();
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + rateapp));
                startActivity(intent1);
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
                System.exit(0);
                //Intent intent = new Intent(AppMainHomeActivity.this, AppThankYouActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //AdsCommon.InterstitialAd(AppMainHomeActivity.this, intent);
            }
        });

        dialog.show();
    }

    //InAppUpdate Code
    private void checkInAppUpdate() {
        appUpdateManager = AppUpdateManagerFactory.create(this);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {

            if(MyApplication.checkInAppUpdate == 0){

                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                    // Request the update.
                    try {
                        appUpdateManager.startUpdateFlowForResult(
                                // Pass the intent that is returned by 'getAppUpdateInfo()'.
                                appUpdateInfo,
                                // an activity result launcher registered via registerForActivityResult
                                AppUpdateType.FLEXIBLE,
                                this,
                                // Or pass 'AppUpdateType.FLEXIBLE' to newBuilder() for
                                // flexible updates.
                                100);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }

                appUpdateManager.registerListener(listener);

            } else {

                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    // Request the update.
                    try {
                        appUpdateManager.startUpdateFlowForResult(
                                // Pass the intent that is returned by 'getAppUpdateInfo()'.
                                appUpdateInfo,
                                // an activity result launcher registered via registerForActivityResult
                                AppUpdateType.IMMEDIATE,
                                this,
                                // Or pass 'AppUpdateType.FLEXIBLE' to newBuilder() for
                                // flexible updates.
                                100);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }

    /*InApp Update Flexible*/
    InstallStateUpdatedListener listener = state -> {
        if(state.installStatus() == InstallStatus.DOWNLOADED){
            popupSnackbarForCompleteUpdate();
        }
    };

    private void popupSnackbarForCompleteUpdate(){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "An Update has just been downloaded.", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("INSTALL", v -> appUpdateManager.completeUpdate());
        snackbar.setActionTextColor(getResources().getColor(android.R.color.holo_blue_bright));
        snackbar.show();
    }


}
