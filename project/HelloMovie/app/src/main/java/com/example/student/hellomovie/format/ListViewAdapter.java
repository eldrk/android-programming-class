package com.example.student.hellomovie.format;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.hellomovie.InfoActivity;
import com.example.student.hellomovie.R;

import java.util.ArrayList;

/**
 * Created by student on 2018-12-13.
 */

public class ListViewAdapter extends BaseAdapter {

    ArrayList<ListViewItem> list; //자료를 저장하고 있는 ArrayList
    Context context;
    int item_layout;
    LayoutInflater layoutInflater;

    public ListViewAdapter(Context context, int item_layout, ArrayList<ListViewItem> list){
        this.context = context;
        this.item_layout = item_layout;
        this.list = list;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(list.size() %2 == 0){
            return list.size() / 2;
        }else {
            return (list.size()/2)+1;
        }

    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {//번호를 리턴
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {//데이터 실제 매칭
        final int pos_l;
        final int pos_r;

        pos_l = (i*2);
        pos_r = (i*2)+1;

        if(view == null){
            view = layoutInflater.inflate(item_layout,viewGroup,false);
        }

        ImageView iv_thumb = (ImageView)view.findViewById(R.id.iv_thumb);
        ImageView iv_thumb1 = (ImageView)view.findViewById(R.id.iv_thumb1);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;


            Bitmap bitmap_thumb = BitmapFactory.decodeResource(context.getResources(),list.get(pos_l).getImg_id(),options);
            Bitmap bitmap_thumb_resize = Bitmap.createScaledBitmap(bitmap_thumb,350,200,true);
            iv_thumb.setImageBitmap(bitmap_thumb_resize);



        iv_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,list.get(pos_l).getTitle()+"를(을) 선택했습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("movie_index",pos_l);
                intent.putExtra("movie_title",list.get(pos_l).getTitle());
                intent.putExtra("movie_date",list.get(pos_l).getDate());
                intent.putExtra("movie_img_id",list.get(pos_l).getImg_id());
                context.startActivity(intent);
            }
        });

        if(pos_r <list.size()){
            Bitmap bitmap_thumb1 = BitmapFactory.decodeResource(context.getResources(),list.get(pos_r).getImg_id(),options);
            Bitmap bitmap_thumb_resize1 = Bitmap.createScaledBitmap(bitmap_thumb1,350,200,true);
            iv_thumb1.setImageBitmap(bitmap_thumb_resize1);
            iv_thumb1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,list.get(pos_r).getTitle()+"를(을) 선택했습니다.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, InfoActivity.class);
                    intent.putExtra("movie_index",pos_r);
                    intent.putExtra("movie_title",list.get(pos_r).getTitle());
                    intent.putExtra("movie_date",list.get(pos_r).getDate());
                    intent.putExtra("movie_img_id",list.get(pos_r).getImg_id());
                    context.startActivity(intent);
                }
            });
        }

        TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
        TextView tv_title1 = (TextView)view.findViewById(R.id.tv_title1);
        tv_title.setText(list.get(pos_l).getTitle());
        tv_title1.setText(list.get(pos_r).getTitle());

        TextView tv_date = (TextView)view.findViewById(R.id.tv_date);
        TextView tv_date1 = (TextView)view.findViewById(R.id.tv_date1);
        tv_date.setText(list.get(pos_l).getDate());
        tv_date1.setText(list.get(pos_r).getDate());
        return view;
    }
}
