package com.example.student.myplayer;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    MediaPlayer player;
    String filepath;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //액티비티에서 브로드캐스팅 된 인텐트를 리시버가 받아서
            //인텐트 안에 저장된 내용을 읽어본다.
            String btn = intent.getStringExtra("btn");
            //int value = intent.getIntExtra("player");

            //서비스에서 액티비티로 응답하기위한 인텐트
            Intent intent1 = new Intent("com.example.student.myplayer");
            if(btn != null) {
                if(btn.equals("play")||btn.equals("pause")) {
                    if(player.isPlaying()){
                        player.pause();

                        intent1.putExtra("state","pause");
                    }else {
                        player.start();

                        //재생기능을 수행한 상태를 인텐트에 기록한다
                        intent1.putExtra("state","play");
                    }
                }
                else if(btn.equals("stop")) {
                    //재생을 정지하는 기능 수행
                    player.stop();
                    try{
                        //중지기능을 수행한 상태를 인텐트에 기록한다
                        intent1.putExtra("state","stop");
                        player.prepare();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
                //수행한 상태를 기록한 인텐트를 패키지내에서 브로드캐스팅한다
                sendBroadcast(intent1);
            }
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //MP3 재생을 위한 MediaPlayer 객체를 생성한다.
        player = new MediaPlayer();
        //액티비티 통신을 위한 리시버를 등록한다
        registerReceiver(receiver,new IntentFilter("com.example.student.myplayer"));
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        //처음실행할때 엑티비티로 부터 받은 인텐트에서 음악파일의 경로를 얻어온다

        //null혹은 SD카드 안의 mp3파일의 경로가 filepath변수에 저장
        filepath = intent.getStringExtra("filepath");

        if(filepath != null){
            try{

                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Intent intent1 = new Intent("com.example.student.myplayer");
                        //실행한 결과를 인텐트에 기록한다
                        intent1.putExtra("state","stop");
                        //인텐트를 패키지 안에서 브로드캐스트를 한다
                        sendBroadcast(intent);
                        //서비스를 종료하는 코드
                        stopSelf();
                    }
                });
                player.setDataSource(filepath);
                player.prepare();


            }catch (Exception e){

            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //서비스가 종료될때 리시버를 등록 해제한다.
        unregisterReceiver(receiver);
    }
}
