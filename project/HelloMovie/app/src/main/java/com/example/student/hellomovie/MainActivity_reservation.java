package com.example.student.hellomovie;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_reservation extends AppCompatActivity {

    Button btn_call,btn_web,btn_map;
    TextView tv5,tv7,tv9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reservation);

        btn_call = (Button)findViewById(R.id.btn_call);
        btn_web = (Button)findViewById(R.id.btn_web);
        btn_map = (Button)findViewById(R.id.btn_map);

        tv5 = (TextView)findViewById(R.id.tv5);
        tv7 = (TextView)findViewById(R.id.tv7);
        tv9 = (TextView)findViewById(R.id.tv9);

        IntentBtnListener intentBtnListener = new IntentBtnListener();

        btn_call.setOnClickListener(intentBtnListener);
        btn_web.setOnClickListener(intentBtnListener);
        btn_map.setOnClickListener(intentBtnListener);


    }

    class IntentBtnListener implements View.OnClickListener{

        Intent intent = null;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_call:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+tv5.getText().toString()));
                    break;
                case R.id.btn_web:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+tv7.getText().toString()));
                    break;
                case R.id.btn_map:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.5026205,127.0239634"));
                    break;
            }
            if(intent !=null){
                startActivity(intent);
            }
        }
    }
}
