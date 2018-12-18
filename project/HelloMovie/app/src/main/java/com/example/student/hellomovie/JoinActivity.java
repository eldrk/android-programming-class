package com.example.student.hellomovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {

    EditText et_id,et_pass,et_password;
    TextView tv2;
    Button btn;
    boolean idChecked;
    boolean passChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        et_id = (EditText)findViewById(R.id.et_id);
        et_pass = (EditText)findViewById(R.id.et_pass);
        et_password = (EditText)findViewById(R.id.et_password);
        tv2 = (TextView)findViewById(R.id.tv2);
        btn = (Button)findViewById(R.id.btn);


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

//                if(et_id.length()>=5 && et_id.length()<=12){
//
//                    tv2.setText("정상적인 아이디 입니다.");
//                    idChecked = true;
//                }
//                else{
//                    tv2.setText("비정상적인 아이디 입니다.");
//                    idChecked = false;
//                }

            }
        });

        et_pass.addTextChangedListener(new JoinActivity.MyTextWatchar());




    }


    class MyTextWatchar implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            tv2.setText("");
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        @Override
        public void afterTextChanged(Editable editable) {
//            String pw = et_pass.getText().toString().trim();
//            String pw_re = et_password.getText().toString().trim();
//            if (pw.equals(pw_re)){
//                tv2.setText("비밀번호가 일치합니다.");
//                passChecked = true;
//            }else{
//                tv2.setText("비밀번호가 일치하지 않습니다.");
//                passChecked = false;
//            }
        }
    }
}

