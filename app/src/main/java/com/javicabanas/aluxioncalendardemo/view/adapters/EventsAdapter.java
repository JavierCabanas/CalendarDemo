package com.javicabanas.aluxioncalendardemo.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.javicabanas.aluxioncalendardemo.R;
import com.javicabanas.aluxioncalendardemo.model.CalendarEvent;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Javi on 20/06/2015.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<CalendarEvent> calendarEventList;


    public EventsAdapter(List<CalendarEvent> calendarEventList) {
        this.calendarEventList = calendarEventList;
    }

    public List<CalendarEvent> getCalendarEventList() {
        return calendarEventList;
    }

    public void setCalendarEventList(List<CalendarEvent> calendarEventList) {
        this.calendarEventList = calendarEventList;
    }

    @Override
    public int getItemCount() {
        return calendarEventList.size();
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        CalendarEvent event = calendarEventList.get(position);
        holder.title.setText(event.getTitle());
        int dayOfWeekResourceId = obtainDayofWeekResourceId(event.getDayOfWeek());
        holder.dayOfWeek.setText(dayOfWeekResourceId);
        holder.dayNumber.setText(Integer.toString(event.getDayOfMonth()));
    }

    private int obtainDayofWeekResourceId(int dayOfWeek) {
        int resourceId;
        switch (dayOfWeek) {
            case 1:
                resourceId = R.string.sunday;
                break;
            case 2:
                resourceId = R.string.monday;
                break;
            case 3:
                resourceId = R.string.tuesday;
                break;
            case 4:
                resourceId = R.string.wednesday;
                break;
            case 5:
                resourceId = R.string.thursday;
                break;
            case 6:
                resourceId = R.string.friday;
                break;
            case 7:
                resourceId = R.string.saturday;
                break;
            default:
                resourceId = R.string.sunday;
                break;
        }
        return resourceId;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View rowView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_item, viewGroup, false);

        //this.context = viewGroup.getContext();
        return new EventViewHolder(rowView);
    }
}

class EventViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.event_title)
    TextView title;
    @InjectView(R.id.event_day_number)
    TextView dayNumber;
    @InjectView(R.id.event_day_of_week)
    TextView dayOfWeek;

    public EventViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}
