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

        ViewHolder holder;

        if (convertView==null){
            convertView = mInflater.inflate(R.layout.item_session_sche_list, parent, false);

            holder = new ViewHolder();
            holder.sessionTime = (TextView)convertView.findViewById(R.id.item_sche_list_time);
            holder.speakerImg = (ImageView)convertView.findViewById(R.id.item_sche_list_speaker_img);
            holder.speakerName = (TextView)convertView.findViewById(R.id.item_sche_list_speaker_name);
            holder.sessionName = (TextView)convertView.findViewById(R.id.item_sche_list_session_title);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.sessionTime.setText(sessionTimes[position]);

        Glide.with(DeviewSchedApplication.GLOBAL_CONTEXT)
                .load(sessionItems.get(position).speakers.get(0).img)
                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                .override(54, 54) //임의로 결정한 크기임.
                .into(holder.speakerImg);

        holder.speakerName.setText(sessionItems.get(position).speakers.get(0).name);
        holder.sessionName.setText(sessionItems.get(position).session_title);

        return convertView;
    }

    public static class ViewHolder{
        public TextView sessionTime;
        public ImageView speakerImg;
        public TextView speakerName;
        public TextView sessionName;
    }
}
