package com.example.student.project6_5;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by student on 2018-11-05.
 */

@SuppressWarnings("deprecation")
public class MainActivity2 extends TabActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TabHost tabHost = getTabHost();


        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("T1").setIndicator("강아지");
        tabSpec1.setContent(R.id.image1);
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("T2").setIndicator("고양이");
        tabSpec2.setContent(R.id.image2);
        tabHost.addTab(tabSpec2);

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("T3").setIndicator("토끼");
        tabSpec3.setContent(R.id.image3);
        tabHost.addTab(tabSpec3);

        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("T4").setIndicator("말");
        tabSpec4.setContent(R.id.image4);
        tabHost.addTab(tabSpec4);

        tabHost.setCurrentTab(0);

    }

}