package com.example.student.doitmission03_05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                startActivityForResult(intent,REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST){
            if(resultCode == RESULT_OK){
                String msg = data.getStringExtra("msg");
                Toast.makeText(getApplicationContext(), "고객관리 엑티비티에서 전달된 메시지" +msg,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
