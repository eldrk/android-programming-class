package com.example.student.project11_4;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Gallery gallery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("갤러리 영화 포스터");

         gallery= findViewById(R.id.gallery);
        MyGalleryAdapter galleryAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(galleryAdapter);




    }

    public class MyGalleryAdapter extends BaseAdapter{
        Context context;
        Integer[] posterId = {
                R.drawable.mov01, R.drawable.mov02, R.drawable.mov05,
                R.drawable.mov18, R.drawable.mov21, R.drawable.mov22,
                R.drawable.mov27, R.drawable.mov34, R.drawable.mov35,
                R.drawable.mov43,R.drawable.mov01, R.drawable.mov02, R.drawable.mov05,
                R.drawable.mov18, R.drawable.mov21, R.drawable.mov22,
                R.drawable.mov27, R.drawable.mov34, R.drawable.mov35,
                R.drawable.mov43,R.drawable.mov01, R.drawable.mov02, R.drawable.mov05,
                R.drawable.mov18, R.drawable.mov21, R.drawable.mov22,
                R.drawable.mov27, R.drawable.mov34, R.drawable.mov35,
                R.drawable.mov43};

        String[] posterName = {
                "써니","완득이","비열한 거리","해리포터 죽음의 성물2",
                "쿵푸팬더","써니","완득이","비열한 거리","해리포터 죽음의 성물2",
                "쿵푸팬더",
                "써니","완득이","비열한 거리","해리포터 죽음의 성물2",
                "쿵푸팬더","써니","완득이","비열한 거리","해리포터 죽음의 성물2",
                "쿵푸팬더","써니","완득이","비열한 거리","해리포터 죽음의 성물2",
                "쿵푸팬더","써니","완득이","비열한 거리","해리포터 죽음의 성물2",
                "써니","완득이","비열한 거리","해리포터 죽음의 성물2",
                "쿵푸팬더","써니","완득이","비열한 거리","해리포터 죽음의 성물2",
                "토이3","미녀는 괴로워"
        };

        public MyGalleryAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return posterId.length;
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
        public View getView(int position, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new Gallery.LayoutParams(100,150));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);

            imageView.setImageResource(posterId[position]);
            final int pos = position;
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    ImageView ivPoster = findViewById(R.id.iv);
                    ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivPoster.setImageResource(posterId[pos]);

                    Toast.makeText(getApplicationContext(),posterName[pos],Toast.LENGTH_SHORT).show();

                    return false;
                }
            });



            return  imageView;
        }


        @Override
        public long getItemId(int i) {
            return 0;
        }
    }
}
