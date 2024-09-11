package com.resumemaker.resumecvbuilder;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {
    private boolean isSwipeEnabled = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setSwipeEnabled(boolean z) {
        this.isSwipeEnabled = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.isSwipeEnabled && super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.isSwipeEnabled && super.onTouchEvent(motionEvent);
    }
}
