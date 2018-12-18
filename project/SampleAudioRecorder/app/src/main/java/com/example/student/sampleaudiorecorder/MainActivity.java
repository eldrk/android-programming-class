package com.example.student.sampleaudiorecorder;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button recordBtn,recordstopBtn,playBtn,playstopBtn;
    private static String RECORDED_FILE ;

    MediaPlayer player;
    MediaRecorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard,"recorded.mp4");
        RECORDED_FILE = file.getAbsolutePath();


        recordBtn=(Button) findViewById(R.id.recordBtn);
        recordstopBtn=(Button) findViewById(R.id.recordstopBtn);
        playBtn=(Button) findViewById(R.id.playBtn);
        playstopBtn=(Button) findViewById(R.id.playstopBtn);

        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recorder !=null){
                    recorder.stop();
                    recorder.release();
                    recorder = null;
                }
                recorder = new MediaRecorder();

                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                recorder.setOutputFile(RECORDED_FILE);

                try{
                    Toast.makeText(getApplicationContext(), "녹음을 시작합니다.", Toast.LENGTH_LONG).show();

                    recorder.prepare();
                    recorder.start();
                }catch (Exception e){
                    Log.e("SampleAudioRecorder","Exception : ",e);
                }
            }
        });

        recordstopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recorder == null)
                    return;

                recorder.stop();
                recorder.release();
                recorder = null;

                Toast.makeText(getApplicationContext(), "녹음이 중지되었습니다.", Toast.LENGTH_LONG).show();

//                ContentValues values = new ContentValues(10);
//                values.put(MediaStore.MediaColumns.TITLE,"Recorded");
//                values.put(MediaStore.Audio.Media.ALBUM,"Audio Album");
//                values.put(MediaStore.Audio.Media.ARTIST,"Mike");
//                values.put(MediaStore.Audio.Media.DISPLAY_NAME,"Recorded Audio");
//                values.put(MediaStore.Audio.Media.IS_RINGTONE,1);
//                values.put(MediaStore.Audio.Media.IS_MUSIC,1);
//                values.put(MediaStore.MediaColumns.DATE_ADDED,System.currentTimeMillis()/1000);
//                values.put(MediaStore.MediaColumns.MIME_TYPE,"audio/mp4");
//                values.put(MediaStore.Audio.Media.DATA,RECORDED_FILE);
//
//                Uri audioUri = getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,values);
//
//                if(audioUri == null){
//                    Log.d("SampleAudioRecoder","Audio insert failed");
//                    return;
//                }

            }
        });


        playBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (player != null) {
                    player.stop();
                    player.release();
                    player = null;
                }

                Toast.makeText(getApplicationContext(), "녹음된 파일을 재생합니다.", Toast.LENGTH_LONG).show();
                try {
                    player = new MediaPlayer();

                    player.setDataSource(RECORDED_FILE);
                    player.prepare();
                    player.start();
                } catch (Exception e) {
                    Log.e("SampleAudioRecorder", "Audio play failed.", e);
                }
            }
        });


        playstopBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (player == null)
                    return;

                Toast.makeText(getApplicationContext(), "재생이 중지되었습니다.", Toast.LENGTH_LONG).show();

                player.stop();
                player.release();
                player = null;
            }
        });

    }

    @Override
    protected void onPause() {
        if(recorder != null){
            recorder.release();
            recorder = null;
        }

        if (player != null) {
            player.release();
            player = null;
        }

        super.onPause();
    }


}
