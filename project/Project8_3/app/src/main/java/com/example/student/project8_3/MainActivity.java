package com.example.student.project8_3;

import java.io.File;

import android.graphics.Color;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final static int CENTER = 1;
    Button btnPrev, btnNext;
    myPictureView myPicture;
    int curNum;
    File[] imageFiles;
    String imageFname;
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지 뷰어");
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);

        myPicture = (myPictureView) findViewById(R.id.mypicture);

        textView = (TextView)findViewById(R.id.textView);

        imageFiles = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()+"/Pictures").listFiles();
        imageFname = imageFiles[0].toString();
        myPicture.imagepath=imageFname;

        textView.setText("1" + "/" + imageFiles.length );
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);
        textView.setGravity(CENTER);



        btnPrev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (curNum <= 0) {
                    // Toast.makeText(getApplicationContext(), "첫번째 그림입니다", Toast.LENGTH_SHORT).show();                           .show();

                    curNum=imageFiles.length-1;




                } else {
                    curNum--;


                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagepath = imageFname;
                    myPicture.invalidate();
                    //invalidate()메소드를 호출하면 myPicture 클래스의 onDraw()가 호출된다.

                    textView.setText((curNum + 1) + "/" + imageFiles.length);
                }

            }
        });










        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (curNum >= imageFiles.length - 1) {
                    //Toast.makeText(getApplicationContext(), "마지막 그림입니다", Toast.LENGTH_SHORT).show();

                    curNum=0;

                } else {
                    curNum++;


                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagepath = imageFname;
                    myPicture.invalidate();

                    textView.setText((curNum + 1) + "/" + imageFiles.length);
                }
            }
        });

    }
}