package com.example.student.hellomovie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity_listView extends AppCompatActivity {

    ListView lv_movielist;
    ArrayList<ListViewItem> arrayList;


    //String[] str = {"라푼젤","주토피아","겨울왕국","쿵푸팬더"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view);

        //2.리스트뷰 객체 만들기
        lv_movielist = (ListView)findViewById(R.id.lv_movielist);

        String url = "http://70.12.110.56:3000";
        HashMap<String,String> map = new HashMap<>();
        map.put("number","1");
//        map.put("number","2");
//        map.put("number","3");
//        map.put("number","4");
//        map.put("number","5");
//        map.put("number","6");
        MyHttpTask myHttpTask = new MyHttpTask(url,map);
        myHttpTask.execute();

        String url_img = "http://70.12.110.56:3000/files";
        MyImageHttpTask myImageHttpTask = new MyImageHttpTask(url_img,map);
        myImageHttpTask.execute();





        //Adapter에 전달할 데이터 구성하기
//        ListViewItem item1 = new ListViewItem("라푼젤","2018,3",R.drawable.tangle);
//        ListViewItem item4 = new ListViewItem("쿵푸팬더","2018,3",R.drawable.pander);


        arrayList = new ArrayList<>();


//        arrayList.add(item1);
//        arrayList.add(new ListViewItem("주토피아","2018,3",R.drawable.zootopia));
//        arrayList.add(new ListViewItem("겨울왕국","2018,3",R.drawable.frozen));
//        arrayList.add(item4);



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
        listViewAdapter.notifyDataSetChanged();

        //4.리스트뷰에 setonItemClickListener 등록하기
        lv_movielist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),str[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity_reservation.class);
                startActivity(intent);
            }
        });


    }

    class MyHttpTask extends AsyncTask<Void,Void,String> {

        String url;
        HashMap<String,String> map;

        public MyHttpTask(String url, HashMap<String, String> map) {
            this.url = url;
            this.map = map;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject root = new JSONObject(s);

                String title = root.getString("title");
                JSONArray director = root.getJSONArray("director");
                String director_str = director.getString(0);

                JSONArray actor = root.getJSONArray("actor");
                String actor_str0 = actor.getString(0);
                String actor_str1 = actor.getString(1);
                String actor_str2 = actor.getString(2);
                String actor_str3 = actor.getString(3);
                String actor_str4 = actor.getString(4);
                String actor_str5 = actor.getString(5);

                JSONArray category = root.getJSONArray("category");
                String category_str0 = category.getString(0);

                int runningTime = root.getInt("runningTime");
                String openDate = root.getString("openDate");

                String str ="title : "+title+"\ndirector : "+director_str
                        +"\nactor : "+actor_str0+","+actor_str1+","+actor_str2+","+actor_str3+","+actor_str4+","+actor_str5
                        +"\ncategory : "+category_str0+ "\nrunningTime : "+runningTime
                        +"\nopenDate : "+openDate;

                //tv.setText(str);
                arrayList.add(new ListViewItem(title,openDate,R.drawable.tangle));


                this.cancel(true);


            }catch (Exception e){
                e.printStackTrace();
            }


        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected String doInBackground(Void... voids) {

            String result = null;
            String post_query = "";
            PrintWriter printWriter = null;
            BufferedReader bufferedReader = null;

            try {
                URL text = new URL(url);
                HttpURLConnection http = (HttpURLConnection)text.openConnection();
                http.setRequestProperty("Content-type",
                        "application/x-www-form-urlencoded;charset=UTF-8");
                http.setConnectTimeout(10000);
                http.setReadTimeout(10000);
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                if(map != null && map.size() > 0) {

                    Iterator<String> keys = map.keySet().iterator();

                    boolean first_query_part = true;
                    while(keys.hasNext()) {

                        if(!first_query_part) {
                            post_query += "&";
                        }

                        String key = keys.next();
                        post_query += (key + "=" + URLEncoder.encode(map.get(key), "UTF-8"));

                        first_query_part = false;
                    }

                    // sending to server
                    printWriter = new PrintWriter(new OutputStreamWriter(
                            http.getOutputStream(), "UTF-8"));
                    printWriter.write(post_query);
                    printWriter.flush();

                    // receive from server
                    bufferedReader = new BufferedReader(new InputStreamReader(
                            http.getInputStream(), "UTF-8"));
                    StringBuffer stringBuffer = new StringBuffer();
                    String line;

                    while((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                    }

                    result = stringBuffer.toString();
                }
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                try{
                    if(printWriter != null) printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if(bufferedReader != null) bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }

    class MyImageHttpTask extends AsyncTask<Void,Void,Bitmap>{

        String url;
        HashMap<String,String> map;

        public MyImageHttpTask(String url, HashMap<String, String> map) {
            this.url = url;
            this.map = map;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //iv.setImageBitmap(bitmap);
            this.cancel(true);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Bitmap result = null;
            String post_query = "";
            PrintWriter printWriter = null;

            try{
                URL text = new URL(url);
                HttpURLConnection http = (HttpURLConnection) text.openConnection();
                http.setRequestProperty("Content-type","application/x-www-form-urlencoded;charset=UTF-8");
                http.setConnectTimeout(10000);
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                if(map != null && map.size()>0){
                    Iterator<String> keys = map.keySet().iterator();

                    boolean first_query_part = true;
                    while(keys.hasNext()) {

                        if(!first_query_part) {
                            post_query += "&";
                        }

                        String key = keys.next();
                        post_query += (key + "=" + URLEncoder.encode(map.get(key), "UTF-8"));

                        first_query_part = false;
                    }

                    // sending to server
                    printWriter = new PrintWriter(new OutputStreamWriter(
                            http.getOutputStream(), "UTF-8"));
                    printWriter.write(post_query);
                    printWriter.flush();

                    // receive from server
                    result = BitmapFactory.decodeStream(http.getInputStream());

                }

            }catch (Exception e){


                e.printStackTrace();
                result = null;
            } finally {
                try{
                    if(printWriter != null) printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;

        }
    }

}
