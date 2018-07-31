package com.kdp.vivocalendar;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private TextView titleView;
    private RecyclerView mRecyclerView;
    private int mCurrentYear; //当前年
    private int mCurrentMonth;//当前月
    private int mCurrentDay;//当前日
    private int mSelectMonth;//当前选中月
    private int mSelectDay;//当前选中日
    private DateInfo mDateInfo;
    private DateAdapter adapter;

    private Button mBeforeView, mNextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initViews();
        //初始化当前日期
        initCurrentDate();
        //初始化日期数据
        initDateData();
        initListener();
        //设置标题
        setDateTitle();
        //设置当前日期

        adapter = new DateAdapter(mDateInfo);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 7));
        mRecyclerView.setAdapter(adapter);
    }

    private void setDateTitle() {
        //设置标题显示当前年月
        String mDateFormat = "%1$d年%2$d月";
        //注意月份从0开始，所以需要加1
        titleView.setText(String.format(mDateFormat, mCurrentYear, mCurrentMonth + 1));
    }

    private void initCurrentDate() {
        //初始化Calendar实例
        Calendar mCalendar = Calendar.getInstance();

        //获取当前的年月日
        mCurrentYear = mCalendar.get(Calendar.YEAR);
        mCurrentMonth = mCalendar.get(Calendar.MONTH);
        mCurrentDay = mCalendar.get(Calendar.DATE);

        mSelectMonth = mCurrentMonth;
        mSelectDay = mCurrentDay;
    }

    private void initListener() {
        mBeforeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentMonth--;
                if (mCurrentMonth < 0) mCurrentMonth = 11;
                swapMonth();
            }
        });

        mNextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentMonth++;
                if (mCurrentMonth > 11) mCurrentMonth = 0;
                swapMonth();
            }
        });
    }

    /**
     * 切换月份
     */
    private void swapMonth() {
        setDateTitle();
        initDateData();
        adapter.setDatas(mDateInfo);
    }

    private void initViews() {
        mBeforeView = findViewById(R.id.before);
        mNextView = findViewById(R.id.next);
        titleView = findViewById(R.id.title);
        mRecyclerView = findViewById(R.id.recylerView);
    }

    private void initDateData() {
        //初始化Calendar实例
        Calendar mCalendar = Calendar.getInstance();
        //先设置当前年月日
        mCalendar.set(Calendar.YEAR, mCurrentYear);
        mCalendar.set(Calendar.MONTH, mCurrentMonth);
        mCalendar.set(Calendar.DATE, 1);
        //获取本月中的最大天数
        int daysOfMonth = getMaxDaysOfMonth(mCalendar.get(Calendar.MONTH));
        Log.e(this.getClass().getSimpleName(), "initDateData: maxDays = " + daysOfMonth);
        //获取一周中的第几天
        int startDayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        //获取上个月份在本月份日历中显示的天数
        int dayOfBeforeMonth = startDayOfWeek - 1;
        //获取上个月最后一天是几号
        int dateOfBeforeMonth = getMaxDaysOfMonth(mCalendar.get(Calendar.MONTH) - 1);
        //获取下个月份在本月份日历中显示的天数
        mCalendar.set(Calendar.DATE, daysOfMonth);
        int endDayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        int dayOfAfterMonth = 7 - endDayOfWeek;
        //设置当前日期
        mDateInfo = new DateInfo();
        mDateInfo.setmCurrentYear(mCurrentYear);
        mDateInfo.setmCurrentMonth(mCurrentMonth);
        mDateInfo.setmCurrentDay(mCurrentDay);
        //设置当前选中的日期
        mDateInfo.setmSelectMonth(mSelectMonth);
        mDateInfo.setmSelectDay(mSelectDay);

        List<Day> mDaysList = new ArrayList<>();
        //添加上个月份在本月份中要显示的日期数据
        for (int i = 0; i < dayOfBeforeMonth; i++) {
            Day day = new Day();
            day.setDate(dateOfBeforeMonth - dayOfBeforeMonth + 1 + i);
            day.setCurrentMonth(false);
            mDaysList.add(day);
        }

        //添加本月份要显示的日期数据
        for (int i = 0; i < daysOfMonth; i++) {
            Day day = new Day();
            day.setDate(i + 1);
            day.setCurrentMonth(true);
            mDaysList.add(day);
        }
        //添加下个月份在本月份中要显示的日期数据
        for (int i = 0; i < dayOfAfterMonth; i++) {
            Day day = new Day();
            day.setDate(i + 1);
            day.setCurrentMonth(false);
            mDaysList.add(day);
        }
        mDateInfo.setmDaysList(mDaysList);

        Log.e(this.getClass().getSimpleName(), "initDateData: " + Arrays.toString(mDaysList.toArray()));
    }


    /**
     * 获取某月中的最大天数
     *
     * @param month
     * @return
     */
    private int getMaxDaysOfMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month + 1);
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取某月中的最大天数
     *
     * @param month
     * @return
     */
    private int getMaxDaysOfMonth2(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getActualMaximum(Calendar.DATE);
    }


}
