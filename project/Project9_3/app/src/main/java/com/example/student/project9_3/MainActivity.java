package com.example.student.project9_3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.lang.Float;

public class MainActivity extends AppCompatActivity {

    ImageButton zoomin, zoomout, rotate, bright, dark, gray;
    MyGraphicView graphicView;
    static float scaleX=1, scaleY=1;
    static float angle = 0;
    static float color = 1;
    static float satur = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("미니포토샵");

        LinearLayout piclayout = (LinearLayout) findViewById(R.id.piclayout);
        graphicView = (MyGraphicView)new MyGraphicView(this);
        piclayout.addView(graphicView);
        clickIcons();


    }



    private  void clickIcons(){

        zoomin = (ImageButton) findViewById(R.id.zoomin);
        zoomout = (ImageButton) findViewById(R.id.zoomout);
        rotate = (ImageButton) findViewById(R.id.rotate);
        bright = (ImageButton) findViewById(R.id.bright);
        dark = (ImageButton) findViewById(R.id.dark);
        gray = (ImageButton) findViewById(R.id.gray);


        zoomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleX = scaleX+0.2f;
                scaleY = scaleY+0.2f;
                graphicView.invalidate();
            }
        });

        zoomout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleX = scaleX-0.2f;
                scaleY = scaleY-0.2f;
                graphicView.invalidate();
            }
        });



        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angle = angle + 20;
                graphicView.invalidate();
            }
        });


        bright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = color + 0.2f;
                graphicView.invalidate();
            }
        });

        dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = color - 0.2f;
                graphicView.invalidate();
            }
        });



        gray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(satur ==0) satur =1;
               else satur =0;
               graphicView.invalidate();
            }
        });



    }


    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.lena256);
            int picX = (this.getWidth() - bitmap.getWidth())/2;
            int picY = (this.getHeight() - bitmap.getHeight())/2;

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;

            Paint paint = new Paint();
            float[] array = {   color   ,0      ,0       ,0      ,0,
                                 0      ,color  ,0       ,0      ,0,
                                 0      ,0      ,color   ,0      ,0,
                                 0      ,0      ,0       ,1      ,0 };
            ColorMatrix cm = new ColorMatrix(array);
            //angle
            if(satur ==0 )cm.setSaturation(satur);


            paint.setColorFilter(new ColorMatrixColorFilter(cm));

            canvas.drawBitmap(bitmap,picX,picY,null);
            //zooin
            canvas.scale(scaleX,scaleY,cenX,cenY);
            //rotate
            canvas.rotate(angle,cenX,cenY);
            //bright
            canvas.drawBitmap(bitmap,picX,picY,paint);


            bitmap.recycle();


        }
    }
}
