package com.resumemaker.resumecvbuilder;

import android.app.Application;
import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;


public class MainApplication extends Application {


    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(1);
    }
}
