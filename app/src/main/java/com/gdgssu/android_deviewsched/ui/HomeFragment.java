package com.gdgssu.android_deviewsched.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdgssu.android_deviewsched.R;

public class HomeFragment extends DeviewFragment {
    private Toolbar mToolbar;
    private ViewPager mPager;

    private static final String APP_NAME_KEY = "APP_NANE";
    private CharSequence appName;

    private String TAG = "HomeFragment";

    public static HomeFragment newInstance(CharSequence appName) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putCharSequence(APP_NAME_KEY, appName);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            appName = getArguments().getString(APP_NAME_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        initToolbar(rootView);
        initFragmentPager(rootView);

        return rootView;
    }

    private void initToolbar(View rootView) {
        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initFragmentPager(View rootView) {
        mPager = (ViewPager) rootView.findViewById(R.id.content_pager);
        PagerFragmentAdapter adpater = new PagerFragmentAdapter(
                getChildFragmentManager(), getActivity());
        mPager.setAdapter(adpater);
    }
}
