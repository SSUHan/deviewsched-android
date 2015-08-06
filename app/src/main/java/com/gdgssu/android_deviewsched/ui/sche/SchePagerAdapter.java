package com.gdgssu.android_deviewsched.ui.sche;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gdgssu.android_deviewsched.DeviewSchedApplication;
import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.model.Session;
import com.gdgssu.android_deviewsched.model.Track;
import com.gdgssu.android_deviewsched.util.GlideCircleTransform;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by flashgugu on 15. 7. 29.
 */
public class SchePagerAdapter extends BaseAdapter {

    private String[] sessionTimes = new String[]{
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

    public SchePagerAdapter(Track track, Context context) {

        this.sessionItems = track.sessions;
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return sessionItems.size();
    }

    @Override
    public Object getItem(int position) {
        return sessionItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SessionViewHolder sessionHolder;
        DayViewHolder dayHolder;
        if (position==0){
            if (convertView==null){
                convertView = mInflater.inflate(R.layout.item_day_sche_list, parent, false);

                dayHolder = new DayViewHolder();
                dayHolder.day = (TextView)convertView.findViewById(R.id.item_day_sche_list_day);
                dayHolder.date = (TextView)convertView.findViewById(R.id.item_date_sche_list_day);

                convertView.setTag(dayHolder);
            }else{
                dayHolder = (DayViewHolder)convertView.getTag();
            }

            dayHolder.day.setText("Day1");
            dayHolder.date.setText("9.14");

        }else{

            if (convertView==null){
                convertView = mInflater.inflate(R.layout.item_session_sche_list, parent, false);

                sessionHolder = new SessionViewHolder();
                sessionHolder.sessionTime = (TextView)convertView.findViewById(R.id.item_sche_list_time);
                sessionHolder.speakerImg = (ImageView)convertView.findViewById(R.id.item_sche_list_speaker_img);
                sessionHolder.speakerName = (TextView)convertView.findViewById(R.id.item_sche_list_speaker_name);
                sessionHolder.sessionName = (TextView)convertView.findViewById(R.id.item_sche_list_session_title);

                convertView.setTag(sessionHolder);

            }else{
                sessionHolder = (SessionViewHolder)convertView.getTag();
            }

            sessionHolder.sessionTime.setText(sessionTimes[position-1]);

            Glide.with(DeviewSchedApplication.GLOBAL_CONTEXT)
                    .load(sessionItems.get(position-1).speakers.get(0).img)
                    .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                    .override(54, 54) //임의로 결정한 크기임.
                    .into(sessionHolder.speakerImg);

            sessionHolder.speakerName.setText(sessionItems.get(position-1).speakers.get(0).name);
            sessionHolder.sessionName.setText(sessionItems.get(position-1).session_title);

        }

        return convertView;
    }

    public static class SessionViewHolder{
        public TextView sessionTime;
        public ImageView speakerImg;
        public TextView speakerName;
        public TextView sessionName;
    }

    public static class DayViewHolder{
        public TextView day;
        public TextView date;
    }
}
