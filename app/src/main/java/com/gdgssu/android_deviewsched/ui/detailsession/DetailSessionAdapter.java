package com.gdgssu.android_deviewsched.ui.detailsession;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.gdgssu.android_deviewsched.R;

import java.util.ArrayList;

public class DetailSessionAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    ArrayList<String> arrayList;
    Context mContext;

    public DetailSessionAdapter(Context context, ArrayList<String> arrayList) {

        this.mContext = context;
        this.arrayList = arrayList;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        QuestionsViewHolder questionsHolder;

        if (convertView==null){
            convertView = mInflater.inflate(R.layout.item_detail_session_question, parent, false);

            questionsHolder = new QuestionsViewHolder();

            questionsHolder.userImg = (ImageView) convertView.findViewById(R.id.item_detail_session_question_userimg);
            questionsHolder.userName = (TextView) convertView.findViewById(R.id.item_detail_session_question_username);
            questionsHolder.questionText = (TextView) convertView.findViewById(R.id.item_detail_session_question_text);

            convertView.setTag(questionsHolder);
        }else{
            questionsHolder = (QuestionsViewHolder)convertView.getTag();
        }

        questionsHolder.questionText.setText(arrayList.get(position));

        return convertView;
    }

    public static class QuestionsViewHolder{
        public ImageView userImg;
        public TextView userName;
        public TextView questionText;

    }
}
