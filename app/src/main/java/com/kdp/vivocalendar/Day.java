package com.kdp.vivocalendar;

public class Day {
    private int date;
    private boolean IsCurrentMonth = true;//是否属于当前月份

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public boolean isCurrentMonth() {
        return IsCurrentMonth;
    }

    public void setCurrentMonth(boolean currentMonth) {
        IsCurrentMonth = currentMonth;
    }
}
