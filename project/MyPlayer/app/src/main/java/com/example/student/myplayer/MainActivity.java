package com.example.student.myplayer;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_start,btn_stop;
    boolean bReadPerm,bWritePerm;
    TextView tv1,tv2;
    ProgressBar pb;
    boolean bstatePlay = false;
    Progress progress;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPermission();

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new MyBtnListener());
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new MyBtnListener());
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        pb = (ProgressBar)findViewById(R.id.pb);


       registerReceiver(receiver,new IntentFilter("com.example.student.myplayer"));


        if (bReadPerm) {
            String state = Environment.getExternalStorageState();

            if (state.equals(Environment.MEDIA_MOUNTED)) {
                try {
                    //SD카드 안에 있는 mp3파일의 경로를 읽어온다
                    String musicPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/music.mp3";
                    Intent intent = new Intent(MainActivity.this,MyService.class);
                    intent.putExtra("filepath",musicPath);

                    startService(intent);
                    //Log.d("PlayMp3", "mp3 file");
                } catch (Exception e) {
                    //Log.d("PlayMp3", "mp3 file error");
                    e.printStackTrace();
                }
            }
        }
    }

    private String transMillsec(int mill){
        String result="";
        int sec = (mill/1000)%60;
        int min = (mill/(1000*60)%60);
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MyPlayer","Main onDestroy()");
        unregisterReceiver(receiver);
    }

    class MyBtnListener implements View.OnClickListener{
        Intent intent;
        @Override
        public void onClick(View v) {
            intent = new Intent("com.example.student.myplayer");
            int value;
            switch (v.getId()){
                case R.id.btn_start:
                    if(!bstatePlay){
                        intent.putExtra("btn","play");
                    }else{

                        btn_start.setText("pause");
                    }
                    break;
                case R.id.btn_stop:
                    intent.putExtra("btn","stop");
                    break;
            }
            sendBroadcast(intent);
        }
    }



    BroadcastReceiver receiver = new BroadcastReceiver() {
        //서비스로 부터 전달된 인텐트를 state라는 키값으로 읽어온다
        //값이 없다면, state변수안에는 null,play,pause,stop
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra("state");
            //int value = intent.getIntExtra("value",value);
            if(state != null){
                if(state.equals("play")){
                    //재생중임을 표시하는 bstateplay변수에 true저장
                    bstatePlay = true;
                    btn_start.setText("Pause");


                }else if(state.equals("pause")||state.equals("stop")){
                    //서비스가 중지 혹은 일시중지 명령을 수행한 결과가 있다면
                    //재생중임을 표시하는 bstateplay변수에 false저장
                    bstatePlay = false;
                    btn_start.setText("Play");
                }

            }

        }
    };

    class Progress extends AsyncTask<Void,Void,Void>{
        Intent intent = new Intent("com.example.student.myplayer");
        @Override
        protected void onPreExecute() {
            intent.putExtra("time","running_time");
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while (isCancelled() == false){
                SystemClock.sleep(500);
                sendBroadcast(intent);
            }
            return null;
        }
    }


    private void setPermission(){
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED){
            bReadPerm = true;
        }



        if(!bReadPerm){
            ActivityCompat.requestPermissions(this,
                    new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 200 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                bReadPerm = true;
            }

        }
    }
}
