package com.gdgssu.android_deviewsched.ui.sche;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdgssu.android_deviewsched.DeviewSchedApplication;
import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.ui.DeviewFragment;
import com.gdgssu.android_deviewsched.ui.home.PagerFragmentAdapter;

public class ScheFragment extends DeviewFragment {

    private static final String KEY_TITLE = "title";

    private ViewPager pager;
    private CharSequence title;

    private String TAG = "ScheFragment";

    public static ScheFragment newInstance(CharSequence title) {
        ScheFragment fragment = new ScheFragment();
        Bundle args = new Bundle();
        args.putCharSequence(KEY_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public ScheFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(KEY_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sche, container, false);

        initToolbar(rootView);
        initFragmentPager(rootView);
        initTabLayout(rootView);

        return rootView;
    }

    private void initTabLayout(View rootView) {
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.sched_tabs);
        tabLayout.setupWithViewPager(pager);
    }

    private void initFragmentPager(View rootView) {
        pager = (ViewPager) rootView.findViewById(R.id.content_pager);
//        final PagerFragmentAdapter adpater = new PagerFragmentAdapter(
//                getChildFragmentManager(), Home.homeObject.homeHomePagerListItems, DeviewSchedApplication.GLOBAL_CONTEXT);
        //Todo 각 Pager에 뿌려질 데이터들의 ArrayList 묶음 객체를 두번째 인자에 던져야함.
//        pager.setAdapter(adpater);
    }

    private void initToolbar(View rootView) {
        Toolbar mToolbar = (Toolbar) rootView.findViewById(R.id.sched_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
