package com.example.student.project6;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    Chronometer chron;
    Button btnStart, btnEnd;
    RadioButton rdoCal, rdoTime;
    CalendarView cal;
    TimePicker time;
    TextView year, month, day, hour, minute;
    int selectYear, selectMonth, selectDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간 예약");

        chron = findViewById(R.id.chron);
        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);
        rdoCal = findViewById(R.id.rdoCal);
        rdoTime = findViewById(R.id.rdoTime);
        cal = findViewById(R.id.cal);
        time = findViewById(R.id.time);
        year = findViewById(R.id.year);
        month = findViewById(R.id.month);
        day = findViewById(R.id.day);
        hour = findViewById(R.id.hour);
        minute = findViewById(R.id.minute);

        time.setVisibility(View.INVISIBLE);
        cal.setVisibility(View.INVISIBLE);

        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.setVisibility(View.INVISIBLE);
                cal.setVisibility(View.VISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.setVisibility(View.VISIBLE);
                cal.setVisibility(View.INVISIBLE);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chron.setBase(SystemClock.elapsedRealtime());
                chron.start();
                chron.setTextColor(Color.RED);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chron.stop();
                chron.setTextColor(Color.BLUE);
                //chron.setText("예약에 걸린 시간 : " +chron.getOnChronometerTickListener());
                year.setText(Integer.toString(selectYear));
                month.setText(Integer.toString(selectMonth));
                day.setText(Integer.toString(selectDay));

                hour.setText(Integer.toString(time.getCurrentHour()));
                minute.setText(Integer.toString(time.getCurrentMinute()));
            }
        });

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                selectYear = i;
                selectMonth = i1+1;
                selectDay = i2;
            }
        });


    }
}
