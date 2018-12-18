package com.example.student.hello2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onBackButtonClicked(View view){
        Toast.makeText(getApplicationContext(),"돌아가지롱",Toast.LENGTH_LONG).show();
        finish();
    }
}
