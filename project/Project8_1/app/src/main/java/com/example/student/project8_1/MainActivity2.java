package com.example.student.project8_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by student on 2018-11-07.
 */

public class MainActivity2 extends AppCompatActivity {

    Button btnRead;
    EditText edit1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnRead = (Button)findViewById(R.id.btnRead);
        edit1 = (EditText) findViewById(R.id.edit1);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    InputStream input = getResources().openRawResource(R.raw.test);
                    byte[] txt = new byte[input.available()];
                    input.read(txt);
                    edit1.setText(new String(txt));
                    input.close();
                }catch (IOException e){

                }
            }
        });

    }
}
