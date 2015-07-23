package com.gdgssu.android_deviewsched.ui.findfriends;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gdgssu.android_deviewsched.DeviewSchedApplication;
import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.model.FindFriendsItem;
import com.gdgssu.android_deviewsched.ui.particleview.DeviewTextView;
import com.gdgssu.android_deviewsched.util.GlideCircleTransform;

import java.util.ArrayList;

public class FindFriendsAdapter extends RecyclerView.Adapter<FindFriendsAdapter.ViewHolder> {

    private ArrayList<FindFriendsItem> findFriendsItems;
    private final OnItemClickListener mOnItemClickListener;

    public FindFriendsAdapter(ArrayList<FindFriendsItem> findFriendsItems, OnItemClickListener listener) {
        this.findFriendsItems = findFriendsItems;
        mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_findfriends_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v);
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        ImageView avatarImage = holder.avatarImage;
//        DeviewTextView userName = holder.userName;

        FindFriendsItem item = findFriendsItems.get(position);

//        Glide.with(DeviewSchedApplication.GLOBAL_CONTEXT)
//                .load(item.imgURL)
//                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
//                .into(avatarImage);

//        userName.setText(item.uesrname);

    }

    @Override
    public int getItemCount() {
        return findFriendsItems.size();
    }

    public static interface OnItemClickListener {

        public void onItemClick(View view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

//        private final ImageView avatarImage;
//        private final DeviewTextView titleText;


        public ViewHolder(View v) {
            super(v);

//            avatarImage = (ImageView) v.findViewById(R.id.list_board_item_avatar);
//            titleText = (DeviewTextView) v.findViewById(R.id.list_board_item_title);
        }
    }
}
