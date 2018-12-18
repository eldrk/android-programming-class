package com.example.student.scrollviewex;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

/**
 * Created by student on 2018-11-02.
 */

public class MainActivity3 extends AppCompatActivity {

    ScrollView scrollView1,scrollView2;
    ImageView imageView2,imageView3;
    BitmapDrawable bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        scrollView1 = (ScrollView)findViewById(R.id.scrollView1);
        scrollView2 = (ScrollView)findViewById(R.id.scrollView2);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        scrollView1.setHorizontalFadingEdgeEnabled(true);
        scrollView2.setHorizontalFadingEdgeEnabled(true);

        Resources res = getResources();
        bitmap = (BitmapDrawable)res.getDrawable(R.drawable.image01);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView2.setImageDrawable(bitmap);
        imageView2.getLayoutParams().width = bitmapWidth;
        imageView2.getLayoutParams().height = bitmapHeight;
    }

    public void onBtn3Clicked(View view){
        imageView2.setVisibility(View.VISIBLE);
        imageView3.setVisibility(View.INVISIBLE);
    }

    public void onBtn4Clicked(View view){
        imageView2.setVisibility(View.INVISIBLE);
        imageView3.setVisibility(View.VISIBLE);
    }


}
