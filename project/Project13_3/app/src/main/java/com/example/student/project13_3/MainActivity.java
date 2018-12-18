package com.example.student.project13_3;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2;
    SeekBar seekbar1,seekbar2;
    TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        seekbar1 = findViewById(R.id.seekbar1);
        seekbar2 = findViewById(R.id.seekbar2);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(){
                    public void run(){
                        for(int i=seekbar1.getProgress();i<100;i+=2){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seekbar1.setProgress(seekbar1.getProgress()+2);
                                    tv1.setText("1번 진행률 : "+seekbar1.getProgress() +"%");
                                }
                            });

                            SystemClock.sleep(100);
                        }
                    }
                }.start();

                new Thread(){
                    public void run(){
                        for(int i=seekbar2.getProgress();i<100;i++){
                            seekbar2.setProgress(seekbar2.getProgress()+1);
                            tv2.setText("2번 진행률 : "+seekbar2.getProgress() +"%");
                            SystemClock.sleep(100);
                        }
                    }
                }.start();


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekbar1.setProgress(0);
                seekbar2.setProgress(0);
                tv1.setText("1번 진행률 : "+seekbar1.getProgress() +"%");
                tv2.setText("2번 진행률 : "+seekbar2.getProgress() +"%");

            }
        });

    }
}
