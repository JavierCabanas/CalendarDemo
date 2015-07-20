package com.javicabanas.aluxioncalendardemo.view.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.javicabanas.aluxioncalendardemo.domain.ObtainCalendarEventsUseCase;
import com.javicabanas.aluxioncalendardemo.model.CalendarEvent;
import com.javicabanas.aluxioncalendardemo.view.CalendarDemoView;
import com.javicabanas.aluxioncalendardemo.view.adapters.EventsAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateChangedListener;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Javi on 20/06/2015.
 */
public class CalendarDemoPresenter implements OnCalendarEventsObtainedListener, OnDateChangedListener {

    private CalendarDemoView calendarDemoView;
    private ObtainCalendarEventsUseCase obtainCalendarEventsUseCase;
    private EventsAdapter eventsAdapter;

    private Context context;

    public CalendarDemoPresenter(Context context, CalendarDemoView calendarDemoView){
        obtainCalendarEventsUseCase= new ObtainCalendarEventsUseCase(context);
        this.context=context;
        this.calendarDemoView=calendarDemoView;
    }


    public void obtainEventsbyDay(Calendar day){
        obtainCalendarEventsUseCase.obtainEventsByDay(day, this);
    }

    @Override
    public void OnEventsObtained(List<CalendarEvent> events) {
        calendarDemoView.showEventList(events);
    }

    @Override
    public void onDateChanged(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date) {
        obtainEventsbyDay(date.getCalendar());
    }
}
