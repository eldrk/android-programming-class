package com.example.student.project10_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by student on 2018-11-12.
 */

public class NewResultActivity extends AppCompatActivity {

    Button btn1,btn2;
    ViewFlipper viewFlipper;


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newresult);
        setTitle("투표 결과");

        Intent intent = getIntent();
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        viewFlipper = findViewById(R.id.viewFliper);
        ImageView image[] = new ImageView[9];
        Integer imageId[] = {R.id.img1,R.id.img2,R.id.img3,R.id.img4,
                R.id.img5,R.id.img6,R.id.img7,R.id.img8,
                R.id.img9};

        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imageName = intent.getStringArrayExtra("ImageName");

        for(int i=0;i<imageId.length;i++) {
            final int index;
            index = i;
            image[index] = findViewById(imageId[index]);
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.startFlipping();
                viewFlipper.setFlipInterval(1000);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.stopFlipping();
            }
        });


    }
}
