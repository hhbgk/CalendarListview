package com.andexert.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class MainActivity extends Activity implements com.andexert.calendarlistview.library.DatePickerController {
    private String tag = getClass().getSimpleName();
    private DayPickerView dayPickerView;
    private int mNumDays = 7;
    protected int mWeekStart = 1;
    private DateFormatSymbols mDateFormatSymbols = new DateFormatSymbols();
    private Calendar mDayLabelCalendar;
    private RecyclerView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (RecyclerView) findViewById(R.id.grid_view);
        mGridView.setLayoutManager(new StaggeredGridLayoutManager(mNumDays, StaggeredGridLayoutManager.VERTICAL));
        dayPickerView = (DayPickerView) findViewById(R.id.pickerView);
        dayPickerView.setController(this);

        List<String> list = new ArrayList<>();
        mDayLabelCalendar = Calendar.getInstance();
        for (int i = 0; i < mNumDays; i++) {
            int calendarDay = (i + mWeekStart) % mNumDays;
            mDayLabelCalendar.set(Calendar.DAY_OF_WEEK, calendarDay);
            String s = mDateFormatSymbols.getShortWeekdays()[mDayLabelCalendar.get(Calendar.DAY_OF_WEEK)].toUpperCase(Locale.getDefault());
            list.add(s);
            Log.w(tag, "s="+s);
        }
        GridAdapter gridAdapter = new GridAdapter(this, list);
        mGridView.setAdapter(gridAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getMaxYear()
    {
        return 2017;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day)
    {
        Log.e("Day Selected", day + " / " + month + " / " + year);
    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays)
    {

        Log.e("Date range selected", selectedDays.getFirst().toString() + " --> " + selectedDays.getLast().toString());
    }
}
