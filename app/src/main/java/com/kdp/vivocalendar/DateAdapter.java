package com.kdp.vivocalendar;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {

    private List<String> mWeekList;
    private List<Day> mDateList;
    private DateInfo mDateInfo;

    public DateAdapter(DateInfo mDateInfo) {
        this.mDateInfo = mDateInfo;
        this.mWeekList = mDateInfo.getmWeeksList();
        this.mDateList = mDateInfo.getmDaysList();
    }

    //更新数据
    public void setDatas(DateInfo mDateInfo) {
        this.mDateInfo = mDateInfo;
        this.mDateList = this.mDateInfo.getmDaysList();
        notifyDataSetChanged();
    }

    @Override
    public DateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
        return new DateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DateViewHolder holder, int position) {
        if (position < mWeekList.size()) {
            holder.textView.setTextColor(Color.BLACK);
            holder.textView.setText(mWeekList.get(position));
            holder.textView.setBackgroundResource(0);
        } else {
            Day day = mDateList.get(position - mWeekList.size());

            if (!day.isCurrentMonth())
                holder.textView.setTextColor(Color.GRAY);
            else {
                holder.textView.setTextColor(Color.BLACK);
                if (day.getDate() == mDateInfo.getmSelectDay() && mDateInfo.getmCurrentMonth() == mDateInfo.getmSelectMonth()) {
                    holder.textView.setBackgroundResource(R.drawable.current_date_shape);
                } else {
                    holder.textView.setBackgroundResource(0);
                }
            }
            holder.textView.setText(String.valueOf(day.getDate()));
        }
    }

    @Override
    public int getItemCount() {
        return mWeekList.size() + mDateList.size();
    }

    class DateViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public DateViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.content);
        }
    }
}
