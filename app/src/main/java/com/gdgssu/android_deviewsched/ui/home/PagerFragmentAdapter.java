package com.gdgssu.android_deviewsched.ui.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerFragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public PagerFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);

        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
