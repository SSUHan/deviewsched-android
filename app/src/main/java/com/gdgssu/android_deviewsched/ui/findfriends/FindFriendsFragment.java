package com.gdgssu.android_deviewsched.ui.findfriends;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.model.FindFriend;
import com.gdgssu.android_deviewsched.ui.DeviewFragment;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class FindFriendsFragment extends DeviewFragment {

    private static final String KEY_TITLE = "title";
    private CharSequence title;

    private LinkedList<String> FriendList;
    private String[] mStrings = {"Joun","Bob","Chaen","Davide","Elem"};

    private PullToRefreshListView mPullRefreshListView;
   // private ArrayAdapter<String> mAdapter;
    private FindFriendsAdapter ffAdapter;

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

        initPullRefresh(rootView);
        initList(rootView);

        return rootView;
    }

    private void initPullRefresh(View rootView){
        mPullRefreshListView = (PullToRefreshListView)rootView.findViewById(R.id.pull_refresh_list);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);


                new GetDataTask().execute();


            }
        });
        mPullRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Toast.makeText(getActivity(), "End of List!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initList(View rootView) {

        ListView actualListView = mPullRefreshListView.getRefreshableView();
        registerForContextMenu(actualListView);

        FriendList = new LinkedList<String>();
        FriendList.addAll(Arrays.asList(mStrings));

        // mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,FriendList);

        ffAdapter = new FindFriendsAdapter(FriendList);


        actualListView.setAdapter(ffAdapter);

//        actualListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "pos:" + position + " id:" + id, Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            return mStrings;
        }

        @Override
        protected void onPostExecute(String[] result) {

            FriendList.add(3,"add after pos 3");
            ffAdapter.notifyDataSetChanged();



            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

}
