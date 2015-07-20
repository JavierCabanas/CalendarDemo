package com.javicabanas.aluxioncalendardemo.view;

import android.content.Context;

import com.javicabanas.aluxioncalendardemo.R;
import com.javicabanas.aluxioncalendardemo.domain.ObtainCalendarEventsUseCase;
import com.javicabanas.aluxioncalendardemo.model.CalendarEvent;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Javi on 21/06/2015.
 */
public class CalendarDecorator implements DayViewDecorator {


    private ObtainCalendarEventsUseCase obtainCalendarEventsUseCase;
    private Context context;
    ArrayList<CalendarEvent> events;

    public CalendarDecorator(Context context) {
        this.context = context;
        obtainCalendarEventsUseCase = new ObtainCalendarEventsUseCase(context);
        events = obtainCalendarEventsUseCase.obtainAllEvents();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return atLeastOneEventOnDay(day);
    }

    private boolean atLeastOneEventOnDay(CalendarDay day) {
        Calendar d = day.getCalendar();
        for (CalendarEvent event : events) {
            if (event.getYear() == d.get(Calendar.YEAR)
                    && event.getDayOfMonth() == d.get(Calendar.DAY_OF_MONTH)
                    && event.getDayOfWeek() == d.get(Calendar.DAY_OF_WEEK)
                    )
                return true;
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(context.getResources().getColor(R.color.accent)));
    }
}
