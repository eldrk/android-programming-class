package com.example.student.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et;

    String num1,num2,operator;

    int[] btn = {R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,R.id.btn10};
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btnClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.et);
        btn0 = (Button)findViewById(R.id.btn0);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btn10 = (Button)findViewById(R.id.btn10);
        btn11 = (Button)findViewById(R.id.btn11);
        btn12 = (Button)findViewById(R.id.btn12);
        btn13 = (Button)findViewById(R.id.btn13);
        btn14 = (Button)findViewById(R.id.btn14);
        btn15 = (Button)findViewById(R.id.btn15);
        btnClear = (Button)findViewById(R.id.btnClear);




//        for(int i=0;i<=10;i++){
//            final int a = i;
//
//        }


        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = et.getText().toString();
                et.setText("");
                operator = "/";
                et.setText("/");


            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = et.getText().toString();
                et.setText("");
                operator = "*";
                et.setText("*");

            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = et.getText().toString();
                et.setText("");
                operator = "-";
                et.setText("-");

            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = et.getText().toString();
                et.setText("");
                operator = "+";
                et.setText("+");

            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num2 = et.getText().toString();
                double result = 0.0;
                switch (operator){

                    case "+":
                        result = Double.parseDouble(num1)+Double.parseDouble(num2);
                        break;
                    case "-":
                        result = Double.parseDouble(num1)-Double.parseDouble(num2);
                        break;
                    case "/":
                        result = Double.parseDouble(num1)/Double.parseDouble(num2);
                        break;
                    case "*":
                        result = Double.parseDouble(num1)*Double.parseDouble(num2);
                        break;
                }
                et.setText(String.valueOf(result));
                num1 = "";
                num2 = "";
                operator = "";


            }
        });



        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1="";
                num2="";
                operator="";
                et.setText("");
            }
        });

    }




}
