package com.example.student.samplevideoplayer;

import android.media.AudioManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button startBtn,volumeBtn;
    private VideoView videoView;
    static final String VIDEO_URL = "https://tv.naver.com/v/2247480";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button) findViewById(R.id.startBtn);
        volumeBtn = (Button) findViewById(R.id.volumeBtn);
        videoView = (VideoView)findViewById(R.id.videoView);

        volumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioManager mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);

                int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolume,AudioManager.FLAG_SHOW_UI);
            }
        });

        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        videoView.setVideoURI(Uri.parse(VIDEO_URL));
        videoView.requestFocus();
    }
}
