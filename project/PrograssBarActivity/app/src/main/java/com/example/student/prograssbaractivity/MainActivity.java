package com.example.student.prograssbaractivity;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_start,btn_end;
    ProgressBar pb_c,pb_b;
    TextView tv;
    MyAsynTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_end = (Button)findViewById(R.id.btn_end);
        pb_c = (ProgressBar)findViewById(R.id.pb_c);
        pb_b = (ProgressBar)findViewById(R.id.pb_b);
        tv = (TextView)findViewById(R.id.tv);

        pb_c.setVisibility(View.VISIBLE);

        btn_start.setOnClickListener(new myBtnListener());
        btn_end.setOnClickListener(new myBtnListener());
    }

    class myBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.btn_start:
                    if(myAsyncTask == null){
                        myAsyncTask = new MyAsynTask();
                        myAsyncTask.execute();
                    }
                    break;
                case R.id.btn_end:
                    if(myAsyncTask != null){
                        myAsyncTask.cancel(true);
                        myAsyncTask = null;
                    }
                    break;
            }
        }
    }


    class MyAsynTask extends AsyncTask<Void,Integer,Void>{

        int value;

        @Override
        protected void onPreExecute() {
            value = 0;
            pb_b.setMax(100);
            pb_b.setProgress(0);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(MainActivity.this,"100%완료 되었습니다.",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            pb_b.setProgress(values[0]);
            Log.d("progressUpdate",values[0].toString());
        }

        @Override
        protected void onCancelled() {
            pb_b.setProgress(0);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while (isCancelled() == false){
                value++;
                SystemClock.sleep(10);

                if(value <=100){
                    publishProgress(value);
                    tv.setText(value);
                }else{
                    break;
                }
            }
            return null;
        }
    }
}
