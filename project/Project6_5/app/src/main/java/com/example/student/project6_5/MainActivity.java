package com.example.student.project6_5;

import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();


        TabHost.TabSpec tabSpecSong = tabHost.newTabSpec("SONG").setIndicator("음악별");
        tabSpecSong.setContent(R.id.tabSong);
        tabHost.addTab(tabSpecSong);

        TabHost.TabSpec tabArtist = tabHost.newTabSpec("Artist").setIndicator("가수별");
        tabArtist.setContent(R.id.tabArtist);
        tabHost.addTab(tabArtist);

        TabHost.TabSpec tabSpecAlbum = tabHost.newTabSpec("Album").setIndicator("앨범별");
        tabSpecAlbum.setContent(R.id.tabAlbum);
        tabHost.addTab(tabSpecAlbum);

        tabHost.setCurrentTab(0);
    }
}
