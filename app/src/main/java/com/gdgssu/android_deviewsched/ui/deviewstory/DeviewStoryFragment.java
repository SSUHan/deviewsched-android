package com.gdgssu.android_deviewsched.ui.deviewstory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.ui.DeviewFragment;

public class DeviewStoryFragment extends DeviewFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String KEY_TITLE = "title";

    // TODO: Rename and change types of parameters
    private CharSequence title;

    // TODO: Rename and change types and number of parameters
    public static DeviewStoryFragment newInstance(CharSequence title) {
        DeviewStoryFragment fragment = new DeviewStoryFragment();
        Bundle args = new Bundle();
        args.putCharSequence(KEY_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public DeviewStoryFragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deview_story, container, false);
    }
}
