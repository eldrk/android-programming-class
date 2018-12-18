package com.example.student.project13_1;

import android.media.MediaPlayer;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer;
    Switch switch1;
    SeekBar sb;
    TextView tv;
    int progress;
    boolean fromUser = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer = MediaPlayer.create(this,R.raw.song1);
        switch1 = findViewById(R.id.switch1);
        sb = findViewById(R.id.sb);
        tv = findViewById(R.id.tv);


        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch1.isChecked() == true){
                    mPlayer.start();
                    new Thread(){
                        public void run(){
                            for(int i=sb.getProgress();i<100;i++){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        sb.setProgress(sb.getProgress()+1);
                                        tv.setText("진행률 : "+sb.getProgress() +"%");
                                    }
                                });

                                SystemClock.sleep(100);
                            }
                        }
                    }.start();

                }else{
                    mPlayer.stop();
                    sb.setProgress(0);

                }
            }

        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
