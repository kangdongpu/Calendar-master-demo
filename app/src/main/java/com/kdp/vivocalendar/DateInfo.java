package com.kdp.vivocalendar;
import java.util.Arrays;
import java.util.List;

public class DateInfo {
    //周
    private List<String> mWeeksList = Arrays.asList("日","一","二","三","四","五","六");
    //日期
    private List<Day> mDaysList;

    //当前年
    private int mCurrentYear;
    //当前月
    private int mCurrentMonth;
    //当前日
    private int mCurrentDay;
    //当前选中月
    private int mSelectMonth = mCurrentMonth;
    //当前选中日
    private int mSelectDay = mCurrentDay;


    public List<Day> getmDaysList() {
        return mDaysList;
    }

    public void setmDaysList(List<Day> mDaysList) {
        this.mDaysList = mDaysList;
    }

    public int getmCurrentYear() {
        return mCurrentYear;
    }

    public void setmCurrentYear(int mCurrentYear) {
        this.mCurrentYear = mCurrentYear;
    }

    public int getmCurrentMonth() {
        return mCurrentMonth;
    }

    public void setmCurrentMonth(int mCurrentMonth) {
        this.mCurrentMonth = mCurrentMonth;
    }

    public int getmCurrentDay() {
        return mCurrentDay;
    }

    public void setmCurrentDay(int mCurrentDay) {
        this.mCurrentDay = mCurrentDay;
    }

    public List<String> getmWeeksList() {
        return mWeeksList;
    }


    public int getmSelectMonth() {
        return mSelectMonth;
    }

    public void setmSelectMonth(int mSelectMonth) {
        this.mSelectMonth = mSelectMonth;
    }

    public int getmSelectDay() {
        return mSelectDay;
    }

    public void setmSelectDay(int mSelectDay) {
        this.mSelectDay = mSelectDay;
    }
}
