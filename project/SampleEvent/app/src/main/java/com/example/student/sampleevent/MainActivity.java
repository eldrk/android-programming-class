package com.example.student.sampleevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView tv;

    GestureDetector detector;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this,"시스템 [BACK]버튼이 눌렸습니다.",Toast.LENGTH_LONG).show();
            tv.setText("");
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.tv);
        View view1 = (View) findViewById(R.id.view1);
        View view2 = (View) findViewById(R.id.view2);

        view1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();

                float curX = motionEvent.getX();
                float curY = motionEvent.getY();

                if(action == MotionEvent.ACTION_DOWN){
                    println("손가락 눌림 : " +curX + ", " +curY);
                }else if(action == MotionEvent.ACTION_MOVE){
                    println("손가락 움직임 : " + curX + ", " +curY);
                }else if(action == MotionEvent.ACTION_UP){
                    println("손가락 뗌 : " + curX + ", " +curY);
                }
                return true;
            }
        });

        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                detector.onTouchEvent(motionEvent);
                return true;
            }
        });

        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                println("onDown() 호출됨.");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                println("onShowPress() 호출됨");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {

                println("onSingleTapUp() 호출됨");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                println("onScroll() 호출됨");
                return true;

            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                println("onScroll() 호출됨");

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                println("onFling() 호출됨");
                return true;
            }
        });



    }


    public void println(String data){
        tv.append(data + "\n");
    }




}
