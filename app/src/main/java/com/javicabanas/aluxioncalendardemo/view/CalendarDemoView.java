package com.javicabanas.aluxioncalendardemo.view;

import com.javicabanas.aluxioncalendardemo.model.CalendarEvent;

import java.util.List;

/**
 * Created by Javi on 20/06/2015.
 */
public interface CalendarDemoView {

    void showEventList(List<CalendarEvent> events);

    void updateTitle(int StringResourceId);
}
