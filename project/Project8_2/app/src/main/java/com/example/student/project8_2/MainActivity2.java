package com.example.student.project8_2;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by student on 2018-11-07.
 */

public class MainActivity2 extends AppCompatActivity {

    Button btn1;
    EditText editlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn1 = (Button) findViewById(R.id.btn1);
        editlist = (EditText) findViewById(R.id.editlist);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sysDir = Environment.getRootDirectory().getAbsolutePath();
                File[] sysFile = (new File(sysDir).listFiles());

                String strFname;
                for (int i = 0; i < sysFile.length; i++) {
                    if (sysFile[i].isDirectory() == true) {
                        strFname = "<폴더>" + sysFile[i].toString();
                    } else {
                        strFname = "<파일>" + sysFile[i].toString();
                    }
                    editlist.setText(editlist.getText() + "\n" + strFname);
                }
            }

        });
    }
}
