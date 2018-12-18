package com.example.student.hello2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onButton1Clicked(View view){
        Toast.makeText(getApplicationContext(),"버튼이지롱",Toast.LENGTH_SHORT).show();
    }

    protected void onButtonClicked(View view){
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(myIntent);
    }

    protected void onButton2Clicked(View view){
        Intent myIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:010-1000-1000"));
        startActivity(myIntent);
    }

    protected void onButton3Clicked(View view){
        Intent myIntet = new Intent(getApplicationContext(),MenuActivity.class);
        startActivity(myIntet);
    }
}
