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

    private LayoutInflater inflater;
    private ArrayList<Session> sessionItems;

    public SchePagerAdapter(Track track) {
        this.sessionItems = track.sessions;
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
//
//        ViewHolder holder;
//
//        if (convertView==null){
//            holder = new ViewHolder();
//
//            inflater = (LayoutInflater)DeviewSchedApplication.GLOBAL_CONTEXT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.item_day_sche_list, null);
//
//            holder.mSpeakerImg = (ImageView)convertView.findViewById(R.id.item_sche_list_speaker_img);
//            holder.mSpeakerName = (TextView)convertView.findViewById(R.id.item_sche_list_speaker_name);
//            holder.mSessionName = (TextView)convertView.findViewById(R.id.item_sche_list_session_title);
//
//            convertView.setTag(holder);
//        }else{
//            holder = (ViewHolder)convertView.getTag();
//        }
//
//        Glide.with(DeviewSchedApplication.GLOBAL_CONTEXT)
//                .load(sessionItems.get(position).speakers.get(0).img)
//                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
//                .into(holder.mSpeakerImg);
//
//        holder.mSpeakerName.setText(sessionItems.get(position).speakers.get(0).name);
//        holder.mSessionName.setText(sessionItems.get(position).session_title);

        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)DeviewSchedApplication.GLOBAL_CONTEXT.getSystemService(DeviewSchedApplication.GLOBAL_CONTEXT.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_session_sche_list, parent, false);

            ImageView speakerImg = (ImageView)convertView.findViewById(R.id.item_sche_list_speaker_img);
            Glide.with(DeviewSchedApplication.GLOBAL_CONTEXT)
                .load(sessionItems.get(position).speakers.get(0).img)
                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                    .fitCenter()
                    .override(40,40)
                .into(speakerImg);

            TextView speakerName = (TextView)convertView.findViewById(R.id.item_sche_list_speaker_name);
            speakerName.setText(sessionItems.get(position).speakers.get(0).name);
            TextView sessionName = (TextView)convertView.findViewById(R.id.item_sche_list_session_title);
            sessionName.setText(sessionItems.get(position).session_title);

        }

        return convertView;
    }

    public class ViewHolder{
        public ImageView mSpeakerImg;
        public TextView mSpeakerName;
        public TextView mSessionName;
    }
}
