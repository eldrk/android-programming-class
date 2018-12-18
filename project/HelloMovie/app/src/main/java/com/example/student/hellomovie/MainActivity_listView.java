package com.example.student.hellomovie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.student.hellomovie.format.ListViewAdapter;
import com.example.student.hellomovie.format.ListViewItem;

import java.util.ArrayList;

public class MainActivity_listView extends AppCompatActivity {

    ListView lv_movielist;

    String[] str = {"라푼젤","주토피아","겨울왕국","쿵푸팬더"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view);

        //2.리스트뷰 객체 만들기
        lv_movielist = (ListView)findViewById(R.id.lv_movielist);

        //Adapter에 전달할 데이터 구성하기
        ListViewItem item1 = new ListViewItem("라푼젤","2018,3",R.drawable.tangle);
        ListViewItem item4 = new ListViewItem("쿵푸팬더","2018,3",R.drawable.pander);


        ArrayList<ListViewItem> arrayList = new ArrayList<>();

        arrayList.add(item1);
        arrayList.add(new ListViewItem("주토피아","2018,3",R.drawable.zootopia));
        arrayList.add(new ListViewItem("겨울왕국","2018,3",R.drawable.frozen));
        arrayList.add(item4);



        //3.리스트뷰에 Adapter등록하기

        /*
        첫번째 매개변수 : 엑티비티 정보(context객체)
        두번째 매개변수 : 리스트뷰 항목의 레이아웃(안드로이드 제공)
        세번째 매개변수 : 표시할 데이터들
         */
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,str);
        //3-1. 새로 만든 어답터를 등록한다
        ListViewAdapter listViewAdapter = new ListViewAdapter(this,R.layout.listview_item,arrayList);

        lv_movielist.setAdapter(listViewAdapter);

        //4.리스트뷰에 setonItemClickListener 등록하기
        lv_movielist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),str[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity_reservation.class);
                startActivity(intent);
            }
        });


    }
}
