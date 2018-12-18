package com.example.student.project9;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    private static class MyGraphicView extends View{
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //MyGraphicView 클래스가 생성되거나 무효화(invalidate)되면 호출되는 메소드
            //일반적으로 화면에 그려질 내용을 이곳에 코딩한다
            super.onDraw(canvas);
            Paint paint = new Paint();
//            paint.setAntiAlias(true);
//            paint.setColor(Color.GREEN);
//            canvas.drawLine(40,40,300,10,paint);

            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(30);
            canvas.drawLine(30,30,300,30,paint);

            paint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawLine(30,80,300,80,paint);

            RectF rectF = new RectF();

            rectF.set(60,120,60+200,100+100);
            canvas.drawOval(rectF,paint);

            rectF.set(60,170,60+100,170+100);
            canvas.drawArc(rectF,80,110,false,paint);

            paint.setColor(Color.BLUE);
            rectF.set(60,280,60+100,280+100);
            canvas.drawRect(rectF,paint);

            paint.setColor(Color.argb(0x88,0xff,0x00,0x00));
            rectF.set(90,310,90+100,310+100);
            canvas.drawRect(rectF,paint);

//            paint.setColor(Color.GRAY);
//            paint.setStrokeWidth(0);//1px두께로 그려진다
//            paint.setStyle(Paint.Style.FILL);
//            Rect rect1 = new Rect(400,500,400+500,500+500);
//            canvas.drawRect(rect1,paint);
//
//            paint.setStyle(Paint.Style.STROKE);
//            Rect rect2 = new Rect(130,200,130+100,200+100);
//            canvas.drawRect(rect2,paint);
//
//            paint.setStyle(Paint.Style.STROKE);
//            RectF rect3 = new RectF(600,600,600+400,600+400);
//            canvas.drawRoundRect(rect3,20,20,paint);
//
//            paint.setStyle(Paint.Style.FILL_AND_STROKE);
//            canvas.drawCircle(600,300,250,paint);
//
//            paint.setStrokeWidth(9);
//            Path path1 = new Path();
//            path1.moveTo(10,290);
//            path1.lineTo(10+50,290+50);
//            path1.lineTo(10+100,290);
//            path1.lineTo(10+150,290+50);
//            path1.lineTo(10+200,290);
//            canvas.drawPath(path1,paint);
//
//            paint.setStrokeWidth(5);
//            paint.setTextSize(70);
//            paint.setColor(Color.BLACK);
//            canvas.drawText("안드로이드",80,500,paint);



        }
    }
}
