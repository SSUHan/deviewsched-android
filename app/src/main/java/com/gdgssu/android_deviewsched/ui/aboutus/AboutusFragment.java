package com.gdgssu.android_deviewsched.ui.aboutus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.ui.DeviewFragment;

public class AboutusFragment extends DeviewFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String KEY_TITLE = "title";

    // TODO: Rename and change types of parameters
    private CharSequence title;

    // TODO: Rename and change types and number of parameters
    public static AboutusFragment newInstance(CharSequence title) {
        AboutusFragment fragment = new AboutusFragment();
        Bundle args = new Bundle();
        args.putCharSequence(KEY_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public AboutusFragment() {
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
        return inflater.inflate(R.layout.fragment_aboutus, container, false);
    }
}
