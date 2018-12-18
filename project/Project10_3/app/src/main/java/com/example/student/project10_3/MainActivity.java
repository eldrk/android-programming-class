package com.example.student.project10_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton rb1,rb2,rb3,rb4,rb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        RadioGroup rg = findViewById(R.id.rg);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit1 = findViewById(R.id.edit1);
                EditText edit2 = findViewById(R.id.edit2);

                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("num1",Integer.parseInt(edit1.getText().toString()));
                intent.putExtra("num2",Integer.parseInt(edit2.getText().toString()));
                startActivityForResult(intent,0);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(rb1.isChecked() == true){
                int hap = data.getIntExtra("Hap",0);
                Toast.makeText(getApplicationContext(),"결과 :" +hap,Toast.LENGTH_SHORT).show();
            }else if(rb2.isChecked()==true){
                int cha = data.getIntExtra("Cha",0);
                Toast.makeText(getApplicationContext(),"결과 :" +cha,Toast.LENGTH_SHORT).show();
            }else if(rb3.isChecked()==true){
                int gob = data.getIntExtra("Gob",0);
                Toast.makeText(getApplicationContext(),"결과 :" +gob,Toast.LENGTH_SHORT).show();
            }else if(rb4.isChecked() ==true){
                int minus = data.getIntExtra("Minus",0);
                Toast.makeText(getApplicationContext(),"결과 :" +minus,Toast.LENGTH_SHORT).show();
            }else{
                int namuge = data.getIntExtra("Namuge",0);
                Toast.makeText(getApplicationContext(),"결과 :" +namuge,Toast.LENGTH_SHORT).show();
            }

        }
    }
}
