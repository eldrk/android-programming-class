package com.example.student.project10_4;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button btnDial,btnWeb,btnGoogle,btnSearch,btnSms,btnPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDial = (Button)findViewById(R.id.btnDial);
        btnWeb = (Button)findViewById(R.id.btnWeb);
        btnGoogle = (Button)findViewById(R.id.btnGoogle);
        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnSms = (Button)findViewById(R.id.btnSms);
        btnPhoto = (Button)findViewById(R.id.btnPhoto);

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://wwww.hahbit.co.kr");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://maps.google.com");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,"안드로이드");
                startActivity(intent);
            }
        });
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri uri = Uri.parse("sms:"+Uri.encode("010-2323-1434"));
                    Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
                    intent.putExtra("sms_boby", "안뇽");
                    //intent.setData(Uri.parse("smsto" + Uri.encode("010-1212-6767")));
                    startActivity(intent);
                }catch (Exception e){

                }
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath()+"/jeju13.jpg"));
                intent.setDataAndType(uri,"image/jpeg");
                startActivity(intent);
            }
        });
    }
}
