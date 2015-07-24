package com.gdgssu.android_deviewsched.ui.findfriends;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.model.FindFriendsItem;
import com.gdgssu.android_deviewsched.ui.DeviewFragment;

import java.util.ArrayList;

public class FindFriendsFragment extends DeviewFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String KEY_TITLE = "title";
    private CharSequence title;

    private ArrayList<FindFriendsItem> findFriendsItems;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    public static FindFriendsFragment newInstance(CharSequence title) {
        FindFriendsFragment fragment = new FindFriendsFragment();
        Bundle args = new Bundle();
        args.putCharSequence(KEY_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public FindFriendsFragment() {

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

        View rootView = inflater.inflate(R.layout.fragment_find_friends, container, false);

        initSwipeRefreshLayout(rootView);
        initList(rootView);

        return rootView;
    }

    private void initSwipeRefreshLayout(View rootView) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.BLACK);
    }

    private void initList(View rootView) {

        ListView mListView = (ListView)rootView.findViewById(R.id.listview);
        FindFriendsAdapter adapter = new FindFriendsAdapter(findFriendsItems);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "DeviewRefresh", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }
}
