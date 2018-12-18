package com.example.student.project5_2;

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
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView textResult;
    Button[] numBtns = new Button[10];
    Integer[] numBtnIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    String num1,num2;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        textResult = findViewById(R.id.textResult);

        for(int i=0; i<numBtnIds.length; i++){
            numBtns[i] = findViewById(numBtnIds[i]);
        }

        btnAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.trim().equals("") || num2.trim().equals("")){
                    Toast.makeText(getApplicationContext(),"값을 입력하세요",Toast.LENGTH_SHORT).show();
                    return false;
                }

                result = Double.parseDouble(num1) + Double.parseDouble(num2);
                textResult.setText("계산결과 : " + result.toString());
                return false;
            }
        });

        btnSub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.trim().equals("") || num2.trim().equals("")){
                    Toast.makeText(getApplicationContext(),"값을 입력하세요",Toast.LENGTH_SHORT).show();
                    return false;
                }
                result = Double.parseDouble(num1) - Double.parseDouble(num2);
                textResult.setText("계산결과 : " + result.toString());
                return false;
            }
        });

        btnMul.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.trim().equals("") || num2.trim().equals("")){
                    Toast.makeText(getApplicationContext(),"값을 입력하세요",Toast.LENGTH_SHORT).show();
                    return false;
                }
                result = Double.parseDouble(num1) * Double.parseDouble(num2);
                textResult.setText("계산결과 : " + result.toString());
                return false;
            }
        });

        btnDiv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.trim().equals("") || num2.trim().equals("")){
                    Toast.makeText(getApplicationContext(),"값을 입력하세요",Toast.LENGTH_SHORT).show();
                    return false;
                }
                result = Double.parseDouble(num1) / Double.parseDouble(num2);
                textResult.setText("계산결과 : " + result.toString());
                return false;
            }
        });

        for(int i=0; i<numBtnIds.length; i++){
            final int index;
            index = i;

            numBtns[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(edit1.isFocused() == true){
                        num1 = edit1.getText().toString()
                                + numBtns[index].getText().toString();
                        edit1.setText(num1);
                    }else if(edit2.isFocused()==true){
                        num2 = edit2.getText().toString()
                                + numBtns[index].getText().toString();
                        edit2.setText(num2);
                    }else{
                        Toast.makeText(getApplicationContext(),"먼저 선택하세요",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }





    }

}