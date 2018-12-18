package com.example.student.project9_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    private  static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(),R.drawable.small);
//            int picX = (this.getWidth() - picture.getWidth()) / 2;
//            int picY = (this.getHeight() - picture.getHeight()) /2;

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;

            Paint paint = new Paint();
//            BlurMaskFilter bMask;
//
//            bMask = new BlurMaskFilter(60,BlurMaskFilter.Blur.OUTER);
//            paint.setMaskFilter(bMask);
//            canvas.drawBitmap(picture,picX,picY,paint);
//            picture.recycle();

//            canvas.drawBitmap(picture,picX,picY,null);
//            picture.recycle();//비트맵 리소스를 해제한다.

//            int cenX = this.getWidth()/2;
//            int cenY = this.getHeight()/2;
//            int picX = (this.getWidth() - picture.getWidth())/2;
//            int picY = (this.getHeight() - picture.getHeight())/2;

//            canvas.rotate(45,cenX,cenY);
//            canvas.drawBitmap(picture,picX,picY,null);
//
//            canvas.translate(-150,200);
//            canvas.drawBitmap(picture,picX,picY,null);

//            canvas.scale(2,2,cenX,cenY);
//            canvas.drawBitmap(picture,picX,picY,null);

//            canvas.skew(0.3f,0.3f);
//            canvas.drawBitmap(picture,picX,picY,null);

//            picture.recycle();

            paint.setColor(Color.GRAY);
            EmbossMaskFilter eMask = new EmbossMaskFilter(new float[]{3,3,3},0.5f,5,10);
            paint.setMaskFilter(eMask);
            canvas.drawCircle(cenX,cenY,150,paint);
        }
    }
}
