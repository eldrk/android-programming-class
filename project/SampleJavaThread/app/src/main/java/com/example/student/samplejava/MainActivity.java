package com.example.student.samplejava;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ProgressBar bar;
    ProgressHandler handler;
    boolean isRunning;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new ProgressHandler();
        tv= (TextView) findViewById(R.id.tv);
        bar = (ProgressBar) findViewById(R.id.bar);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStop();
            }
        });

        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStart();
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        bar.setProgress(0);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int i=0; i<20&& isRunning;i++){
                        Thread.sleep(1000);
                        Message msg = handler.obtainMessage();
                        handler.sendMessage(msg);

                    }

                }catch (Exception e){
                    Log.e("MainActivity","Exception in processing message",e);
                }
            }
        });
        isRunning = true;
        thread1.start();
    }





    @Override
    protected void onStop() {
        super.onStop();

        isRunning = false;
    }

    public class ProgressHandler extends Handler{
        public void handleMessage(Message msg){
            bar.incrementProgressBy(5);
            if(bar.getProgress() == bar.getMax()){
                tv.setText("Done");
            }else{
                tv.setText("Working ..." +bar.getProgress());
            }
        }
    }
}
