package com.example.student.project4_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1,edit2;
    Button btnAdd, btnSub, btnMul, btnDiv, btn;
    TextView text1,text2;
    String num1, num2;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 계산기");

        edit1 = (EditText)findViewById(R.id.edit1);
        edit2 = (EditText)findViewById(R.id.edit2);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnSub = (Button)findViewById(R.id.btnSub);
        btnMul = (Button)findViewById(R.id.btnMul);
        btnDiv = (Button)findViewById(R.id.btnDiv);
        btn = (Button)findViewById(R.id.btn);
        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.trim().equals("") || num2.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력하세요", Toast.LENGTH_LONG).show();
                }
                else{
                    result = Double.parseDouble(num1) + Double.parseDouble(num2);
                    text2.setText( result.toString());
                }

            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.trim().equals("") || num2.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력하세요", Toast.LENGTH_LONG).show();
                }
                else {
                    result = Double.parseDouble(num1) - Double.parseDouble(num2);
                    text2.setText(result.toString());
                }
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.trim().equals("") || num2.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력하세요", Toast.LENGTH_LONG).show();
                }
                else {
                    result = Double.parseDouble(num1) * Double.parseDouble(num2);
                    text2.setText(result.toString());
                }

            }
        });


        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.trim().equals("") || num2.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력하세요", Toast.LENGTH_LONG).show();
                }
                else {
                    result = Double.parseDouble(num1) / Double.parseDouble(num2);
                    text2.setText(result.toString());
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num2.trim().equals("0")){
                    Toast.makeText(getApplicationContext(), "0으로 나눌수 없습니다", Toast.LENGTH_LONG).show();
                }
                else {
                    result = Double.parseDouble(num1) % Double.parseDouble(num2);
                    text2.setText(result.toString());
                }

            }
        });
    }
}