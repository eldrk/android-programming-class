package com.example.student.ratingbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv3,tv4;
    RatingBar rb1,rb2;
    SeekBar sb1,sb2,sb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);

        rb1 = (RatingBar)findViewById(R.id.rb1);
        rb2 = (RatingBar)findViewById(R.id.rb2);

        sb1 = (SeekBar)findViewById(R.id.sb1);
        sb2 = (SeekBar)findViewById(R.id.sb2);
        sb3 = (SeekBar)findViewById(R.id.sb3);


        rb1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(),"첫번째  점수는 : " +rating,Toast.LENGTH_SHORT).show();
            }
        });

        tv3.setText("두번째 점수는 : " + rb2.getRating());

        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv4.setText("vol : "+progress);
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
