package com.resumemaker.resumecvbuilder;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class UtilSharedPreferences {
    static SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public String myPreferences = "myPrefs";

    public UtilSharedPreferences(Context context) {
        SharedPreferences sharedPreferences2 = context.getSharedPreferences("myPrefs", 0);
        sharedPreferences = sharedPreferences2;
        this.editor = sharedPreferences2.edit();
    }

    public void setTemplatePos(int i) {
        this.editor.putInt("templatePos", i);
        this.editor.commit();
    }

    public int getTemplatePos() {
        return sharedPreferences.getInt("templatePos", 1);
    }

    public void setBilling(int i) {
        this.editor.putInt("appBilling", i);
        this.editor.commit();
    }

    public int getBilling() {
        return sharedPreferences.getInt("appBilling", 0);
    }

    public void setIsHowToUseShown(boolean z) {
        this.editor.putBoolean("isHowToUseShown", z);
        Log.i("checkUse", "" + z);
        this.editor.commit();
    }

    public boolean getIsHowToUseShown() {
        return sharedPreferences.getBoolean("isHowToUseShown", false);
    }

    public void setAdPlacementInfo(boolean z) {
        this.editor.putBoolean("adPlacementInfo", z);
        this.editor.commit();
    }

    public boolean getAdPlacementInfo() {
        return sharedPreferences.getBoolean("adPlacementInfo", false);
    }

    public void setPermissionStatus(boolean z) {
        this.editor.putBoolean("permissionStatus", z);
        this.editor.commit();
    }

    public boolean getPermissionStatus() {
        return sharedPreferences.getBoolean("permissionStatus", false);
    }

    public void setCompressionInfo(boolean z) {
        this.editor.putBoolean("compressionInfo", z);
        this.editor.commit();
    }

    public boolean getCompressionInfo() {
        return sharedPreferences.getBoolean("compressionInfo", false);
    }

    public void setIssueStatus(boolean z) {
        this.editor.putBoolean("issueStatus", z);
        this.editor.commit();
    }

    public boolean getIssueStatus() {
        return sharedPreferences.getBoolean("issueStatus", false);
    }

    public void setWaterMarkRemovalInfo(int i) {
        this.editor.putInt("waterMarkRemovalType", i);
        this.editor.commit();
    }

    public int getWaterMarkRemovalInfo() {
        return sharedPreferences.getInt("waterMarkRemovalType", 0);
    }

    public void setInterstitialAdCount(int i) {
        this.editor.putInt("interstitialAdCount", i);
        this.editor.commit();
    }

    public int getInterstitialAdCount() {
        return sharedPreferences.getInt("interstitialAdCount", 1);
    }

    public void setshowInst(boolean z) {
        this.editor.putBoolean("showinst", z);
        this.editor.commit();
    }

    public boolean getshowInst() {
        return sharedPreferences.getBoolean("showinst", true);
    }

    public void setAdNetworkBanner(Long l) {
        this.editor.putLong("adNetworkBanner", l.longValue());
        this.editor.commit();
    }

    public void setshowInstSplash(boolean z) {
        this.editor.putBoolean("showinstsplash", z);
        this.editor.commit();
    }

    public boolean getshowInstSplash() {
        return sharedPreferences.getBoolean("showinstsplash", true);
    }

    public void setshowBennar(boolean z) {
        this.editor.putBoolean("showinstbennar", z);
        this.editor.commit();
    }

    public boolean getshowBennar() {
        return sharedPreferences.getBoolean("showinstbennar", true);
    }

    public long getAdNetworkBanner() {
        return sharedPreferences.getLong("adNetworkBanner", 1);
    }

    public void setAdNetworkInlineBanner(Long l) {
        this.editor.putLong("adNetworkInlineBanner", l.longValue());
        this.editor.commit();
    }

    public long getAdNetworkInlineBanner() {
        return sharedPreferences.getLong("adNetworkInlineBanner", 1);
    }

    public void setAdNetworkInterstitial(Long l) {
        this.editor.putLong("adNetworkInterstitial", l.longValue());
        this.editor.commit();
    }

    public long getAdNetworkInterstitial() {
        return sharedPreferences.getLong("adNetworkInterstitial", 1);
    }

    public void setAdNetworkNativeAd(Long l) {
        this.editor.putLong("adNetworkNativeAd", l.longValue());
        this.editor.commit();
    }

    public long getAdNetworkNativeAd() {
        return sharedPreferences.getLong("adNetworkNativeAd", 1);
    }

    public void setAdNetworkRewardedVideo(Long l) {
        this.editor.putLong("adNetworkRewardedVideo", l.longValue());
        this.editor.commit();
    }

    public long getAdNetworkRewardedVideo() {
        return sharedPreferences.getLong("adNetworkRewardedVideo", 1);
    }

    public void setAdAppOpen(Boolean bool) {
        this.editor.putBoolean("adAppOpen", bool.booleanValue());
        this.editor.commit();
    }

    public Boolean getAdAppOpen() {
        return Boolean.valueOf(sharedPreferences.getBoolean("adAppOpen", true));
    }
}
