package com.javicabanas.aluxioncalendardemo.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.javicabanas.aluxioncalendardemo.R;
import com.javicabanas.aluxioncalendardemo.model.CalendarEvent;
import com.javicabanas.aluxioncalendardemo.view.adapters.EventsAdapter;
import com.javicabanas.aluxioncalendardemo.view.presenters.CalendarDemoPresenter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Javi on 20/06/2015.
 */
public class CalendarDemoActivity extends AppCompatActivity implements CalendarDemoView, OnMonthChangedListener {

    @InjectView(R.id.calendar_events_list)
    RecyclerView calendarEventList;
    @InjectView(R.id.include_toolbar)
    Toolbar toolbar;
    @InjectView(R.id.toolbar_title)
    TextView toolbarTitle;
    @InjectView(R.id.calendar)
    MaterialCalendarView calendar;
    @InjectView(R.id.content)
    LinearLayout content;

    private EventsAdapter eventListAdapter;
    private CalendarDemoPresenter presenter;
    private RecyclerView.LayoutManager layoutManager;

    private boolean monthVisible;
    int height = -1;

    private DayViewDecorator dayViewDecorator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.inject(this);
        presenter = new CalendarDemoPresenter(this, this);
        layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        calendarEventList.setLayoutManager(layoutManager);
        initToolbar();
        initCalendar();
        monthVisible = true;
    }

    private void initCalendar(){
        calendar.setOnDateChangedListener(presenter);
        calendar.setOnMonthChangedListener(this);
        calendar.addDecorator(new CalendarDecorator(this));
        calendar.invalidateDecorators();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        int monthNumber=Calendar.getInstance().get(Calendar.MONTH);
        int monthNameId= getMonthNameResourceId(monthNumber);
        updateTitle(monthNameId);

    }


    @Override
    protected void onResume() {
        super.onResume();
        Calendar today = Calendar.getInstance();
        Log.d("Day", today.toString());
        presenter.obtainEventsbyDay(today);
        calendar.setCurrentDate(today);
        calendar.setSelectedDate(today);
    }

    @Override
    public void showEventList(List<CalendarEvent> events) {
        if (eventListAdapter == null) {
            eventListAdapter = new EventsAdapter(events);
            calendarEventList.setAdapter(eventListAdapter);
        } else {
            eventListAdapter.setCalendarEventList(events);
            eventListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        int id = getMonthNameResourceId(date.getMonth());
        updateTitle(id);
    }

    private int getMonthNameResourceId(int monthNumber) {
        int id;
        switch (monthNumber) {
            case 0:
                id = R.string.jan;
                break;
            case 1:
                id = R.string.feb;
                break;
            case 2:
                id = R.string.mar;
                break;
            case 3:
                id = R.string.apr;
                break;
            case 4:
                id = R.string.may;
                break;
            case 5:
                id = R.string.jun;
                break;
            case 6:
                id = R.string.jul;
                break;
            case 7:
                id = R.string.aug;
                break;
            case 8:
                id = R.string.sep;
                break;
            case 9:
                id = R.string.oct;
                break;
            case 10:
                id = R.string.nov;
                break;
            case 11:
                id = R.string.dec;
                break;
            default:
                id = R.string.jan;
                break;
        }
        return id;
    }

    @Override
    public void updateTitle(int resourceId) {
        toolbarTitle.setText(resourceId);
    }
}
