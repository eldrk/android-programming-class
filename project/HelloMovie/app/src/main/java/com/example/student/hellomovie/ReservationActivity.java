package com.example.student.hellomovie;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.student.hellomovie.format.BookDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReservationActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn_cancel,btn_reservation;
    TextView tv1,tv2,tv4,tv5;
    SeekBar sb1;
    ArrayList<BookDate> list;
    Intent intent;
    String title_val;
    boolean reservation;

    int year,month,day,hour,min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);
        btn_reservation = (Button)findViewById(R.id.btn_reservation);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv4 = (TextView)findViewById(R.id.tv4);
        tv5 = (TextView)findViewById(R.id.tv5);
        sb1 = (SeekBar)findViewById(R.id.sb1);


        GregorianCalendar cal = new GregorianCalendar();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);

        intent=getIntent();
        title_val =intent.getStringExtra("val_title");
        tv5.setText(title_val);

        GregorianCalendar movie_start_time0 = new GregorianCalendar(2018,11,11,13,30);
        GregorianCalendar movie_start_time1 = new GregorianCalendar(2018,12,11,13,30);
        GregorianCalendar movie_start_time2 = new GregorianCalendar(2018,12,12,13,30);
        GregorianCalendar movie_start_time3 = new GregorianCalendar(2018,11,14,13,30);



        list = new ArrayList<>();
        list.add(new BookDate(movie_start_time0,"2","라푼젤",30,28));
        list.add(new BookDate(movie_start_time1,"2:30","쿵푸팬더",30,28));
        list.add(new BookDate(movie_start_time2,"2","주토피아",30,28));
        list.add(new BookDate(movie_start_time3,"3:30","겨울왕국",30,28));



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ReservationActivity.this,dateSetListener,year,month,day).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(ReservationActivity.this,timeSetListener,hour,month,false).show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb1.setProgress(0);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reservation){
                    Intent intent = new Intent(getApplicationContext(),MainActivity_reservation.class);
                    startActivity(intent);
                }

            }
        });

        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv4.setText("좌석수 : " + progress);

                for (int i = 0; i <= list.size(); i++) {
                    if (list.get(i).getTitle().equals(title_val)) {
                        if ((list.get(i).getTotalSeat() - list.get(i).getResSeat() >= progress)) {
                            reservation = true;
                        } else {
                            Toast.makeText(getApplicationContext(), "좌석이 부족하여 선택할수 없습니다", Toast.LENGTH_LONG).show();
                            reservation = false;
                        }
                    }
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            // 월은 0부터 시작된다
//            Toast.makeText(getApplicationContext(), i + "/" + (i1+1) + "/"+ i2,
//                    Toast.LENGTH_LONG).show();
            tv1.setText(year + "/" + (month+1) + "/"+ day);

//            for(int i=0;i<=list.size();i++){
//                if(list.get(i).getTitle().equals(title_val)){
//                    if (list.get(i).getMovie_start_time().equals(year)
//                            && list.get(i).getMovie_start_time().equals(month)
//                            && list.get(i).getMovie_start_time().equals(day)){
//                        reservation = true;
//
//                    }else{
//                        Toast.makeText(getApplicationContext(), "상영시간이 지나 예매하실수 없습니다.", Toast.LENGTH_LONG).show();
//                        reservation =false;
//                        }
//                    }
//                }
            }
        };


    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int min) {
//            Toast.makeText(getApplicationContext(), i + ":" + i1,
//                    Toast.LENGTH_LONG).show();
            tv2.setText(hour + ":" + min);

//            for(int i=0;i<=list.size();i++){
//                if(list.get(i).getTitle().equals(title_val)){
//                    if (list.get(i).getMovie_start_time().equals(hour)
//                            && list.get(i).getMovie_start_time().equals(min)){
//                        reservation = true;
//
//                    }else{
//                        Toast.makeText(getApplicationContext(), "상영시간이 지나 예매하실수 없습니다.", Toast.LENGTH_LONG).show();
//                        reservation = false;
//                    }
//                }
//            }
        }
    };


}
