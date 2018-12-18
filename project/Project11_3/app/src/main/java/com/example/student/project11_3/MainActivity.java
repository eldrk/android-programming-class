package com.example.student.project11_3;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gv = findViewById(R.id.gv);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);





    }

            public class MyGridAdapter extends BaseAdapter {
        Context context;


        Integer[] posterId = {
                R.drawable.mov01, R.drawable.mov02, R.drawable.mov05,
                R.drawable.mov18, R.drawable.mov21, R.drawable.mov22,
                R.drawable.mov27, R.drawable.mov34, R.drawable.mov35,
                R.drawable.mov43, R.drawable.mov01, R.drawable.mov02,
                R.drawable.mov05,
                R.drawable.mov18, R.drawable.mov21, R.drawable.mov22,
                R.drawable.mov27, R.drawable.mov34, R.drawable.mov35,
                R.drawable.mov43, R.drawable.mov01, R.drawable.mov02,
                R.drawable.mov05,
                R.drawable.mov18, R.drawable.mov21, R.drawable.mov22,
                R.drawable.mov27, R.drawable.mov34, R.drawable.mov35,
                R.drawable.mov43, R.drawable.mov01, R.drawable.mov02,
                R.drawable.mov05,
                R.drawable.mov18, R.drawable.mov21, R.drawable.mov22,
                R.drawable.mov27, R.drawable.mov34, R.drawable.mov35,
                R.drawable.mov43
        };

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

        public MyGridAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return posterId.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200,300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);

            imageView.setImageResource(posterId[position]);
            final int pos =position;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View dialogView = View.inflate(MainActivity.this,R.layout.dialog,null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterId[pos]);
                    dlg.setTitle(posterName[pos]);
                    dlg.setIcon(R.drawable.if_multimedia_audio);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기",null);
                    dlg.show();
                }
            });
            return  imageView;




        }


    }
}
