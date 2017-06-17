package com.greatcoding.android.courseregisterationhelper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LouisJH on 2017-06-14.
 */

public class NoticeListAdapter extends BaseAdapter {
    private Context context;
    private List<Notice> noticeList;

    public NoticeListAdapter(Context context, List<Notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int position) {
        return noticeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.notice, null);
        TextView noticeTextView = (TextView) v.findViewById(R.id.noticeTextView);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView dateTextView = (TextView) v.findViewById(R.id.dateTextView);

        noticeTextView.setText(noticeList.get(position).getNotice());
        nameTextView.setText(noticeList.get(position).getName());
        dateTextView.setText(noticeList.get(position).getDate());

        v.setTag(noticeList.get(position).getNotice());
        return v;

    }
}
