package com.example.student.project7;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRead, btnWrite;
        btnRead = (Button)findViewById(R.id.btnRead);
        btnWrite = (Button)findViewById(R.id.btnWrite);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileOutputStream fos = openFileOutput("file.txt",
                            Context.MODE_PRIVATE);
                    //file.txt로 파일을 쓰기모드로 연다
                    //경로는 /data/data/패키지명/file/file.txt
                    //파일 모드에 쓰기를 위해 MODE_PRIVATE나 MODE_APPEND를 사용할수있다
                    String str = "쿡북 안드로이드";
                    fos.write(str.getBytes()); //위의 문자열을 파일에 쓴다
                                               //이때 문자열을 getByye()를 이용하여 byte[]형으로 변환
                    fos.close();
                    Toast.makeText(getApplicationContext(),"file.txt가 생성됨",Toast.LENGTH_SHORT).show();
                }catch (IOException e){}
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileInputStream fis = openFileInput("file.txt");
                    byte[] txt = new byte[30];
                    fis.read(txt);
                    String str = new String(txt);
                    Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                    fis.close();

                }catch (IOException e){
                    Toast.makeText(getApplicationContext(),"파일 없음",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
