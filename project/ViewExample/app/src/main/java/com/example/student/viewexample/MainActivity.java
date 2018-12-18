
package com.example.student.viewexample;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv1, tv2, tv3;

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

        tv1.setText("안녕");
        tv1.setTextColor(Color.GRAY);
        tv2.setTextSize(30);
        tv2.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD_ITALIC);
        tv3.setText("히히히히히");
        tv3.setSingleLine();
    }
}
