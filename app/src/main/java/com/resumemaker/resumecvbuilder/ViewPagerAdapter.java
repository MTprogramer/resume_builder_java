package com.resumemaker.resumecvbuilder;

import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    Fragment currentFragment = null;

    public int getCount() {
        return 7;
    }

    public CharSequence getPageTitle(int i) {
        String str = i == 0 ? "Personal Info" : null;
        if (i == 1) {
            str = "Objective";
        }
        if (i == 2) {
            str = "Education";
        }
        if (i == 3) {
            str = "Experience";
        }
        if (i == 4) {
            str = "Skills";
        }
        if (i == 5) {
            str = "Projects";
        }
        return i == 6 ? "Choose Template" : str;
    }

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public Fragment getItem(int i) {
        if (i == 0) {
            this.currentFragment = new PersonalInfo_Fragment();
            Log.d("key", "message print" + this.currentFragment);
        } else if (i == 1) {
            this.currentFragment = new Objective_Fragment();
        } else if (i == 2) {
            this.currentFragment = new Education_Fragment();
        } else if (i == 3) {
            this.currentFragment = new Experience_Fragment();
        } else if (i == 4) {
            this.currentFragment = new Skills_Fragment();
        } else if (i == 5) {
            this.currentFragment = new Project_Fragment();
        } else if (i == 6) {
            this.currentFragment = new Template_Fragment();
        }
        return this.currentFragment;
    }
}
