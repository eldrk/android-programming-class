package com.example.student.project10_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    Button btn;
    RadioGroup btn3;
    RadioButton btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn3 = findViewById(R.id.btn3);
        btn1 = findViewById(R.id.btn1);
        btn1 = findViewById(R.id.btn2);



//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Class layout;
//
//                if(btn3.getCheckedRadioButtonId() == R.id.btn1){
//                    layout = SecondActivity.class;
//                }else{
//                    layout = ThirdActivity.class;
//                }
//
//                Intent intent = new Intent(getApplicationContext(),layout);
//                startActivity(intent);
//            }
//        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;


                if(btn1.isChecked() == true){
                    intent = new Intent(getApplicationContext(),SecondActivity.class);
                }else{
                    intent = new Intent(getApplicationContext(),ThirdActivity.class);
                }


                startActivity(intent);
            }
        });
    }
}
