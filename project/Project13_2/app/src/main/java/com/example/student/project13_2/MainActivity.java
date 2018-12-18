package com.example.student.project13_2;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnPlay,btnStop,btnPause;
    TextView tv1,tv2;
    SeekBar sb;


    ArrayList<String> mp3List;
    String selectedMp3;

    String mp3Path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Music/";
    MediaPlayer mplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        listView = findViewById(R.id.listView);
        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        sb = findViewById(R.id.sb);
        mp3List = new ArrayList<String>();

        File[] listFiles = new File(mp3Path).listFiles();
        String fileName,extName;
        for(File file : listFiles){
            fileName = file.getName();
            extName = fileName.substring(fileName.length()-3);
            if(extName.equals((String)"mp3")){
                mp3List.add(fileName);
            }
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice,mp3List);
        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        listView.setItemChecked(0,true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMp3 = mp3List.get(i);
            }
        });

        selectedMp3 = mp3List.get(0);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                try{
                   mplayer = new MediaPlayer();
                   mplayer.setDataSource(mp3Path + selectedMp3);
                   mplayer.prepare();
                   mplayer.start();
                   btnPlay.setClickable(false);
                   btnStop.setClickable(true);
                   tv1.setText("실행중인 음악: " +selectedMp3);

                    new Thread(){
                        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
                        public  void  run(){
                            if(mplayer==null)return;
                            sb.setMax(mplayer.getDuration());
                            while (mplayer.isPlaying()){

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            sb.setProgress(mplayer.getCurrentPosition());
                                            tv2.setText("진행시간 : " + timeFormat.format(mplayer.getCurrentPosition()));
                                            mplayer.start();


                                        }

                                    });
                                    SystemClock.sleep(200);
                                }

                        }
                    }.start();

                }catch (IOException e){}
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mplayer.stop();
                mplayer.reset();
                btnPlay.setClickable(true);
                btnStop.setClickable(false);
                tv1.setText("실행중인 음악 : ");

                sb.setProgress(0);
                tv1.setText("진행 시간 : ");

            }
        });

        btnStop.setClickable(false);

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mplayer.seekTo(i);
                }

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
