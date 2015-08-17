package com.gdgssu.android_deviewsched.ui.sche;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gdgssu.android_deviewsched.DeviewSchedApplication;
import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.model.Track;
import com.gdgssu.android_deviewsched.ui.detailsession.DetailSessionActivity;

public class SchePagerFragment extends Fragment {

    private static final String TAG = "SchePagerFragment";

    private Track mTrackData;

    public static SchePagerFragment newInstance(Track track) {
        SchePagerFragment fragment = new SchePagerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG, track);
        fragment.setArguments(bundle);

        return fragment;
    }

    public SchePagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            mTrackData = (Track)getArguments().getSerializable(TAG);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sche_pager, container, false);

        initScheListView(rootView);

        return rootView;
    }

    private void initScheListView(View rootView) {
        ListView listview = (ListView) rootView.findViewById(R.id.fragment_sche_pager_list);
        SchePagerAdapter adapter = new SchePagerAdapter(mTrackData, DeviewSchedApplication.GLOBAL_CONTEXT);

        //임시로 아이템을 누르면 테스트중인 액티비티가 뜨게 만들어놓음
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getActivity().startActivity(new Intent(getActivity(), DetailSessionActivity.class));
            }
        });
        listview.setAdapter(adapter);

    }
}
