package com.example.student.hellomovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    Button btn;
    ImageView iv_1;
    TextView tv1,tv2;
    String val_title;
    String val_date;
    int val_img_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        btn = (Button)findViewById(R.id.btn);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        iv_1 = (ImageView)findViewById(R.id.iv_1);

        Intent intent = getIntent();
        if(intent != null){
            val_img_id = intent.getIntExtra("movie_img_id",-1);
            val_date = intent.getStringExtra("movie_date");
            val_title = intent.getStringExtra("movie_title");
            tv1.setText(val_title);
            tv2.setText(val_date);
            iv_1.setImageResource(val_img_id);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),ReservationActivity.class);
                intent1.putExtra("val_title",val_title);
                startActivity(intent1);

            }
        });


    }
}
