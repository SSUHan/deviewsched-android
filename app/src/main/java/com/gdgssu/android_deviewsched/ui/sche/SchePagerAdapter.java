package com.gdgssu.android_deviewsched.ui.sche;

import android.app.ActionBar;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gdgssu.android_deviewsched.DeviewSchedApplication;
import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.model.Session;
import com.gdgssu.android_deviewsched.model.Track;
import com.gdgssu.android_deviewsched.util.GlideCircleTransform;

import java.util.ArrayList;

/**
 * Created by flashgugu on 15. 7. 29.
 */
public class SchePagerAdapter extends BaseAdapter {

    /**
     * Todo:Keynote시간까지 포함하여 9:30 ~ 9:50을 넣어야 함. 2014년 기준으로 하루에 한트랙에 8개의 세션이 존재함.
     */
    private String[] sessionTimes = new String[]{
            "10:00~10:45",
            "11:00~11:45",
            "12:00~12:45",
            "13:00~13:45",
            "14:00~14:45",
            "15:00~15:45",
            "16:00~16:45",
            "10:00~10:45",
            "11:00~11:45",
            "12:00~12:45",
            "13:00~13:45",
            "14:00~14:45",
            "15:00~15:45",
            "16:00~16:45"
    };
    private LayoutInflater mInflater;
    private ArrayList<Session> sessionItems;
    private Context mContext;

    public SchePagerAdapter(Track track, Context context) {

        this.sessionItems = track.sessions;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return sessionItems.size() + 2;
    }

    @Override
    public Object getItem(int position) {
        return sessionItems.get(position);
    }

    //오류의 소지가 있음
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SessionViewHolder sessionHolder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_session_sche_list, parent, false);

            sessionHolder = new SessionViewHolder();
            sessionHolder.sessionTime = (TextView) convertView.findViewById(R.id.item_sche_list_time);
            sessionHolder.speakerImg = (ImageView) convertView.findViewById(R.id.item_sche_list_speaker_img);
            sessionHolder.speakerImgSecond = (ImageView) convertView.findViewById(R.id.item_sche_list_speaker_img_second);
            sessionHolder.speakerName = (TextView) convertView.findViewById(R.id.item_sche_list_speaker_name);
            sessionHolder.sessionName = (TextView) convertView.findViewById(R.id.item_sche_list_session_title);

            sessionHolder.dayView = (RelativeLayout) convertView.findViewById(R.id.item_session_sche_list_day);
            sessionHolder.dayText = (TextView) convertView.findViewById(R.id.item_day_sche_list_day);
            sessionHolder.dateText = (TextView) convertView.findViewById(R.id.item_date_sche_list_day);

            convertView.setTag(sessionHolder);

        } else {
            sessionHolder = (SessionViewHolder) convertView.getTag();
        }

        if (position >= 0 && position <= 7) {
            if (position == 0) {
                sessionHolder.dayView.setVisibility(View.VISIBLE);
                sessionHolder.dayText.setText("Day 1");
                sessionHolder.dateText.setText("9.14");
            } else {
                Session sessionItem = sessionItems.get(position - 1);
                sessionHolder.dayView.setVisibility(View.INVISIBLE);
                sessionHolder.sessionTime.setText(sessionTimes[position - 1]);

                if (sessionItem.speakers.size() > 1) {
                    setTwoSpeakerInfo(sessionHolder, sessionItem);
                } else {
                    setOneSpeakerInfo(sessionHolder, sessionItem);
                }

                sessionHolder.sessionName.setText(sessionItem.session_title);
            }
        } else if (position >= 8) {
            if (position == 8) {
                sessionHolder.dayView.setVisibility(View.VISIBLE);
                sessionHolder.dayText.setText("Day 2");
                sessionHolder.dateText.setText("9.15");
            } else {
                Session sessionItem = sessionItems.get(position - 2);
                sessionHolder.dayView.setVisibility(View.INVISIBLE);
                sessionHolder.sessionTime.setText(sessionTimes[position - 2]);

                if (sessionItem.speakers.size() > 1) {
                    setTwoSpeakerInfo(sessionHolder, sessionItem);
                } else {
                    setOneSpeakerInfo(sessionHolder, sessionItem);
                }

                sessionHolder.sessionName.setText(sessionItem.session_title);
            }
        }

        return convertView;
    }

    public void setOneSpeakerInfo(SessionViewHolder sessionHolder, Session sessionItem) {
        sessionHolder.speakerImgSecond.setVisibility(View.GONE);

        Glide.with(DeviewSchedApplication.GLOBAL_CONTEXT)
                .load(sessionItem.speakers.get(0).img)
                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                .override(54, 54) //임의로 결정한 크기임.
                .into(sessionHolder.speakerImg);

        sessionHolder.speakerName.setText(sessionItem.speakers.get(0).name);
    }

    private void setTwoSpeakerInfo(SessionViewHolder sessionHolder, Session sessionItem) {
        sessionHolder.speakerImgSecond.setVisibility(View.VISIBLE);

        Glide.with(DeviewSchedApplication.GLOBAL_CONTEXT)
                .load(sessionItem.speakers.get(0).img)
                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                .override(54, 54) //임의로 결정한 크기임.
                .into(sessionHolder.speakerImg);

        Glide.with(DeviewSchedApplication.GLOBAL_CONTEXT)
                .load(sessionItem.speakers.get(1).img)
                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                .override(54, 54) //임의로 결정한 크기임.
                .into(sessionHolder.speakerImgSecond);

        sessionHolder.speakerName.setText(sessionItem.speakers.get(0).name+"/"+sessionItem.speakers.get(1).name);
    }

    public static class SessionViewHolder {

        public TextView sessionTime;
        public ImageView speakerImg;
        public ImageView speakerImgSecond;
        public TextView speakerName;
        public TextView sessionName;

        public RelativeLayout dayView;
        public TextView dayText;
        public TextView dateText;

    }
}
