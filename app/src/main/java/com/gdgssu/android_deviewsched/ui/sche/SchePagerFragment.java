package com.gdgssu.android_deviewsched.ui.sche;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gdgssu.android_deviewsched.R;

public class SchePagerFragment extends Fragment {


    public static SchePagerFragment newInstance() {
        SchePagerFragment fragment = new SchePagerFragment();

        return fragment;
    }

    public SchePagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sche_pager, container, false);

        initScheListView(rootView);

        return inflater.inflate(R.layout.fragment_sche_pager, container, false);
    }

    private void initScheListView(View rootView) {
        ListView listview = (ListView) rootView.findViewById(R.id.fragment_sche_pager_list);

        SchePagerAdapter adapter = new SchePagerAdapter();
        //Todo 인자로 listview의 데이터에 해당하는 객체 리스트를 전달해야함.

        listview.setAdapter(adapter);
    }
}
