package com.example.student.project10_2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by student on 2018-11-13.
 */

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final GridView gv = findViewById(R.id.gv);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);
    }

    public class MyGridAdapter extends BaseAdapter{

        Context context;

        Integer[] imageId = {
                R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,
                R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,
                R.drawable.pic7,R.drawable.pic8,R.drawable.pic9,
        };

        public MyGridAdapter(Context context) {
            this.context = context;
        }

        @Nullable
        @Override
        public CharSequence[] getAutofillOptions() {
            return new CharSequence[0];
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(300,400));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);
            imageView.setImageResource(imageId[i]);

            return imageView;
        }

        @Override
        public int getCount() {
            return imageId.length;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
    }
}


