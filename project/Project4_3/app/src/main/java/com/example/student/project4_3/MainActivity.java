package com.example.student.project4_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text1,text2;
    CheckBox chbox;
    RadioGroup rgroup;
    RadioButton rbtn1, rbtn2, rbtn3;
    Button btn1;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        chbox = findViewById(R.id.chbox);
        rgroup = findViewById(R.id.rgroup);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        btn1 = findViewById(R.id.btn1);
        img = findViewById(R.id.img);


        chbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chbox.isChecked()){
                    text2.setVisibility(View.VISIBLE);
                    rgroup.setVisibility(View.VISIBLE);
                    btn1.setVisibility(View.VISIBLE);
                    img.setVisibility(View.VISIBLE);
                }
                else {
                    text2.setVisibility(View.INVISIBLE);
                    rgroup.setVisibility(View.INVISIBLE);
                    btn1.setVisibility(View.INVISIBLE);
                    img.setVisibility(View.INVISIBLE);
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
