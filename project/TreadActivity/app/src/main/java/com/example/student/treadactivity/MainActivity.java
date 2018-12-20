package com.example.student.treadactivity;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_start, btn_stop;
    TextView tv;
    ProgressBar pb;
    int value = 0;
    Thread thread = null;
    MyTask myTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_stop = (Button)findViewById(R.id.btn_stop);
        tv = (TextView)findViewById(R.id.tv);
        pb = (ProgressBar)findViewById(R.id.pb);

        btn_start.setOnClickListener(new BtnListener());
        btn_stop.setOnClickListener(new BtnListener());

    }

//    Handler handler = new Handler();
//    class UIUpdate implements Runnable{
//        @Override
//        public void run() {
//
//            if(value <1000){
//                tv.setText(Integer.toString(value));
//                pb.setProgress(value);
//            }else{
//                tv.setText("1000번을 카운트 하였습니다.");
//                pb.setProgress(value);
//            }
//        }
//    }

//    class Mythread implements Runnable{
//        @Override
//        public void run() {
//            try{
//                while(!Thread.currentThread().isInterrupted()){
//                    if(value <1000){
//                        SystemClock.sleep(1000);
//                        value++;
//                        handler.post(new UIUpdate());
//                    }
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

//    class Mythread extends Thread{
//        @Override
//        public void run() {
//           try{
//               while(!Thread.currentThread().isInterrupted()){
//                   if(value <1000){
//                        SystemClock.sleep(1000);
//                        value++;
//                        handler.post(new UIUpdate());
//                    }
//                }
//           }catch (Exception e){
//                e.printStackTrace();
//
//           }
//        }
//    }

    class MyTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            value=0;
            pb.setProgress(value);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            tv.setText("1000번을 카운트하셨습니다.");
            pb.setProgress(value);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            pb.setProgress(value);
            tv.setText(Integer.toString(value));
        }

        @Override
        protected void onCancelled() {
            tv.setText("사용자에 의해 종료되었습니다.");
            pb.setProgress(0);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while(isCancelled() == false){
                value++;
                if(value <=1000){
                    publishProgress();
                }
                else {
                    break;
                }
                try{
                    Thread.sleep(1000);
                }catch (Exception e){}
            }
            return null;
        }
    }

    class BtnListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_start:
                    if(myTask == null){
                        myTask  = new MyTask();
                        myTask.execute();
                    }else{
                        Toast.makeText(MainActivity.this,"이미 동작하고 있습니다.",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_stop:
                    if(myTask !=null){
                        myTask.cancel(true);
                        myTask = null;
                    }
                    break;
            }

        }
    }
}
