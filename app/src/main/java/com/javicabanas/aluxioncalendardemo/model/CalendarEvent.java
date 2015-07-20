package com.javicabanas.aluxioncalendardemo.model;

/**
 * Created by Javi on 20/06/2015.
 */
public class CalendarEvent {

    private String title;
    private int dayOfWeek;
    private int dayOfMonth;
    private int year;

    public CalendarEvent(String title, int dayOfWeek, int dayOfMonth, int year) {
        this.title = title;
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = dayOfMonth;
        this.year=year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
