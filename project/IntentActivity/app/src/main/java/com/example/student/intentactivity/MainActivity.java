package com.example.student.intentactivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Button btnWeb,btnPhone,btnMap,btnContent;
    EditText etNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWeb = (Button)findViewById(R.id.btnWeb);
        btnPhone = (Button)findViewById(R.id.btnPhone);
        btnMap = (Button)findViewById(R.id.btnMap);
        btnContent = (Button)findViewById(R.id.btnContent);
        etNum = (EditText)findViewById(R.id.etNum);

        IntentBtnListener intentBtnListener = new IntentBtnListener();

        btnWeb.setOnClickListener(intentBtnListener);
        btnPhone.setOnClickListener(intentBtnListener);
        btnMap.setOnClickListener(intentBtnListener);
        btnContent.setOnClickListener(intentBtnListener);


    }

    class IntentBtnListener implements View.OnClickListener{

        Intent intent = null;
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnWeb:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
                    break;
                case R.id.btnPhone:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+etNum.getText().toString()));
                    break;
                case R.id.btnMap:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:36.6349120,127.4869820"));
                    break;
                case R.id.btnContent:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                    break;
            }
            if(intent !=null){
                startActivity(intent);
            }


        }
    }
}
