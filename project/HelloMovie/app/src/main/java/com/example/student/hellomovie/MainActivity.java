package com.example.student.hellomovie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText et_id,et_pass;
    TextView tv2;
    Button btn_join,btn_login;
    CheckBox chbox;
    boolean idChecked;
    boolean passChecked;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_id = (EditText)findViewById(R.id.et_id);
        et_pass = (EditText)findViewById(R.id.et_pass);
        tv2 = (TextView)findViewById(R.id.tv2);
        btn_join = (Button)findViewById(R.id.btn_join);
        btn_login = (Button)findViewById(R.id.btn_login);


        SharedPreferences sharedPref = getSharedPreferences("filename", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("key1", "woosung");
        editor.putString("key2", "1234");
        editor.putString("key3", "test");
        editor.putInt("key4", 1234);


        Set<String> arr = new HashSet<String>();
        arr.add("hi");
        arr.add("android");
        editor.putStringSet("key6", arr);
        editor.commit();


        et_id.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tv2.setText("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(et_id.toString().equals("woosung")){

                    tv2.setText("아이디가 일치합니다");
                    idChecked = true;
                }
                else{
                    tv2.setText("아이디가 일치하지 않습니다.");
                    idChecked = false;
                }

            }
        });



        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,JoinActivity.class);
                startActivity(intent);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity_listView.class);
                if(idChecked && passChecked){
                    Toast.makeText(MainActivity.this,"로그인 되셨습니다",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"로그인 정보를 확인해주세요",Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);
            }
        });

    }



    class MyTextWatchar implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        @Override
        public void afterTextChanged(Editable editable) {

            if (et_pass.toString().equals("1234")){
                tv2.setText("비밀번호가 일치합니다.");
                passChecked = true;
            }else{
                tv2.setText("비밀번호가 일치하지 않습니다.");
                passChecked = false;
            }
        }
    }
}
