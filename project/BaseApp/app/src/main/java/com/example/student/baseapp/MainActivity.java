package com.example.student.baseapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText edit1;
    Button btnToast, btnHomepage;
    RadioButton nouga, oreo;
    ImageView ivAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("좀 그럴듯한 응용프로그램");

        edit1= (EditText) findViewById(R.id.edit1);
        btnToast=findViewById(R.id.btnToast);
        btnHomepage=findViewById(R.id.btnHomepage);
        nouga=findViewById(R.id.nouga);
        oreo=findViewById(R.id.oreo);
        ivAndroid=findViewById(R.id.ivAndroid);

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), edit1.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });

        btnHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(edit1.getText().toString()));
                startActivity(intent);
            }
        });


        nouga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivAndroid.setImageResource(R.drawable.nougat);
            }
        });

        oreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivAndroid.setImageResource(R.drawable.oreo);
            }
        });




    }
}
