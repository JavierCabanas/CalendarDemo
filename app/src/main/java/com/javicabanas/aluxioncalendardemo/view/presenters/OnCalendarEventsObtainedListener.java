package com.javicabanas.aluxioncalendardemo.view.presenters;

import com.javicabanas.aluxioncalendardemo.model.CalendarEvent;

import java.util.List;

/**
 * Created by Javi on 20/06/2015.
 */
public interface OnCalendarEventsObtainedListener {

    void OnEventsObtained(List<CalendarEvent> events);
}
