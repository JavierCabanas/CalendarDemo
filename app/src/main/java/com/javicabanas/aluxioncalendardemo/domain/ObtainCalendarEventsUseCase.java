package com.javicabanas.aluxioncalendardemo.domain;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

import com.javicabanas.aluxioncalendardemo.model.CalendarEvent;
import com.javicabanas.aluxioncalendardemo.view.presenters.OnCalendarEventsObtainedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Javi on 20/06/2015.
 */
public class ObtainCalendarEventsUseCase {

    private static final String[] FIELDS = {
            CalendarContract.Events.CALENDAR_ID,
            CalendarContract.Events.TITLE,
            CalendarContract.Events.DTSTART,
            CalendarContract.Events.DTEND,
            CalendarContract.Events.DESCRIPTION //TODO get rid of unused parameters
    };

    private static final Uri EVENTS_URI = CalendarContract.Events.CONTENT_URI;
    private Context context;

    public ObtainCalendarEventsUseCase(Context context) {
        this.context = context;
    }


    public void obtainEventsByDay(Calendar day, OnCalendarEventsObtainedListener listener) {
        ArrayList<CalendarEvent> events = obtainEventsByDaySynchronously(day);
        listener.OnEventsObtained(events);
    }

    public ArrayList<CalendarEvent> obtainEventsByDaySynchronously(Calendar day) {
        Cursor cursor = getCursor();
        ArrayList<CalendarEvent> events = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Date eventStartDate = new Date(cursor.getLong(2));
                Calendar event = Calendar.getInstance();
                event.setTime(eventStartDate);

                if (event.get(Calendar.YEAR) == day.get(Calendar.YEAR)
                        && event.get(Calendar.DAY_OF_MONTH) == day.get(Calendar.DAY_OF_MONTH)
                        && event.get(Calendar.DAY_OF_WEEK) == day.get(Calendar.DAY_OF_WEEK)
                        ) {
                    String eventTitle = cursor.getString(1);
                    int eventDayofWeek = event.get(Calendar.DAY_OF_WEEK);
                    int eventDayOfMonth = event.get(Calendar.DAY_OF_MONTH);
                    int year = event.get(Calendar.YEAR);
                    events.add(new CalendarEvent(eventTitle, eventDayofWeek, eventDayOfMonth,year));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return events;
    }

    private Cursor getCursor() {
        ContentResolver contentResolver = context.getContentResolver();
        return contentResolver.query(EVENTS_URI, FIELDS, null, null, null);
    }

    public ArrayList<CalendarEvent> obtainAllEvents() {
        ArrayList<CalendarEvent> events = new ArrayList<>();
        Cursor cursor = getCursor();
        if (cursor.moveToFirst()) {
            do {
                Date eventStartDate = new Date(cursor.getLong(2));
                Calendar event = Calendar.getInstance();
                event.setTime(eventStartDate);
                String eventTitle = cursor.getString(1);
                int eventDayofWeek = event.get(Calendar.DAY_OF_WEEK);
                int eventDayOfMonth = event.get(Calendar.DAY_OF_MONTH);
                int year = event.get(Calendar.YEAR);
                events.add(new CalendarEvent(eventTitle, eventDayofWeek, eventDayOfMonth,year));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return events;
    }
}
