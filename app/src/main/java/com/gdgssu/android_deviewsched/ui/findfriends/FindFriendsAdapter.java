package com.gdgssu.android_deviewsched.ui.findfriends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gdgssu.android_deviewsched.DeviewSchedApplication;
import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.model.FindFriend;
import com.gdgssu.android_deviewsched.ui.particleview.DeviewTextView;
import com.gdgssu.android_deviewsched.util.GlideCircleTransform;

import java.util.ArrayList;

public class FindFriendsAdapter extends BaseAdapter {

    private Context mContext;

    private ArrayList<FindFriend> items;
    private LayoutInflater mInflater;

    public FindFriendsAdapter(ArrayList<FindFriend> findFriendses) {
        this.mContext = DeviewSchedApplication.GLOBAL_CONTEXT;
        this.items = findFriendses;
        this.mInflater = (LayoutInflater.from(this.mContext));
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView avatarView = null;
        DeviewTextView userNameView = null;

        FindFriend item = items.get(position);

        if (convertView==null){
            convertView = mInflater.inflate(R.layout.list_findfriends_item, null);

            Glide.with(mContext)
                    .load(item.getImgURL())
                    .transform(new GlideCircleTransform(mContext))
                    .into(avatarView);

            userNameView.setText(item.getUserName());
        }
        return convertView;
    }
}
