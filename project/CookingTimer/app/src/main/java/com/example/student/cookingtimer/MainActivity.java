package com.example.student.cookingtimer;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_count;
    EditText et1,et2;
    Button btn_30s,btn_10m,btn_30m,btn_reset,btn_start,btn_stop;
    CookTimeAsyncTask timer;
    Thread thread;
    int min,sec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_count = (TextView)findViewById(R.id.tv_count);
        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        btn_30s = (Button)findViewById(R.id.btn_30s);
        btn_10m = (Button)findViewById(R.id.btn_10m);
        btn_30m = (Button)findViewById(R.id.btn_30m);
        btn_reset = (Button)findViewById(R.id.btn_reset);
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_stop = (Button)findViewById(R.id.btn_stop);
        btn_30s.setOnClickListener(new btnListener());
        btn_10m.setOnClickListener(new btnListener());
        btn_30m.setOnClickListener(new btnListener());
        btn_start.setOnClickListener(new btnListener());
        btn_stop.setOnClickListener(new btnListener());
        btn_reset.setOnClickListener(new btnListener());

    }


    Handler handler = new Handler();

    class CookTimeAsyncTask implements Runnable{
        @Override
        public void run() {
            if(sec<60){
                tv_count.setText("0"+min+":"+sec);
            }else if(min<60){

            }
        }
    }

    class MyThread implements Runnable{
        @Override
        public void run() {
            try{
                while(!Thread.currentThread().isInterrupted()){
                    if(sec<60){
                        SystemClock.sleep(1000);
                        sec--;
                        handler.post(new CookTimeAsyncTask());
                    }else if(min<100){
                        SystemClock.sleep(1000);
                        handler.post(new CookTimeAsyncTask());
                    }
                }

            }catch (Exception e){

            }
        }
    }


    class btnListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_30s:

                    min=0;
                    sec+=30;
                    if(sec>60){
                        min=sec/60;
                        sec=30;
                    }

                    et1.setText(Integer.toString(min));
                    et2.setText(Integer.toString(sec));
                    break;

                case R.id.btn_10m:
                    min+=1;
                    sec=0;

                    et1.setText(Integer.toString(min));
                    et2.setText(Integer.toString(sec));
                    break;

                case R.id.btn_30m:
                    min+=30;
                    sec=0;
                    et1.setText(Integer.toString(min));
                    et2.setText(Integer.toString(sec));
                    break;

                case R.id.btn_reset:
                    et1.setText("");
                    et2.setText("");
                    tv_count.setText("00:00");

                    break;

                case R.id.btn_start:
                    MyThread runnable = new MyThread();
                    thread = new Thread(runnable);
                    thread.start();
                    break;

                case R.id.btn_stop:
                    thread.interrupt();
                    tv_count.setText(min+":"+sec);
                    break;
            }
        }
    }


}
