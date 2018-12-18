package com.example.student.project6_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPrev, btnNext;
        final ViewFlipper vfl;

        btnPrev = (Button)findViewById(R.id.btnPrev);
        btnNext = (Button)findViewById(R.id.btnNext);
        vfl = (ViewFlipper)findViewById(R.id.vfl);


        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vfl.showPrevious();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vfl.showNext();
            }
        });
    }
}
