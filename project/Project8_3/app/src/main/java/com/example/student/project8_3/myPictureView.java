package com.example.student.project8_3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by student on 2018-11-07.
 */

public class myPictureView extends View {

    String imagepath = null;

    public myPictureView(Context context, AttributeSet attrs) {

        super(context, attrs);

    }
        @Override
        protected void onDraw (Canvas canvas){
            super.onDraw(canvas);
            //canvas.scale(4,4,0,0);
            if (imagepath != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(imagepath);
                int cenX = this.getWidth()/2;
                int cenY = this.getHeight()/2;
                int picX = (this.getWidth() - bitmap.getWidth()) / 2;
                int picY = (this.getHeight() - bitmap.getHeight()) / 2;

                canvas.scale(2,2,cenX,cenY);
                canvas.drawBitmap(bitmap, picX, picY, null);
                bitmap.recycle();
            }
        }
    }

