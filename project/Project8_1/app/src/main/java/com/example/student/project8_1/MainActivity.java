package com.example.student.project8_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker date;
    EditText edit;
    Button btn;
    String file;
    File mydir;
    SQLiteDatabase sqlDB;
    myDBHelper myHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장");

        date = (DatePicker)findViewById(R.id.date);
        edit = (EditText)findViewById(R.id.edit);
        btn = (Button)findViewById(R.id.btn);

        final Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        myHelper = new myDBHelper(this);

//        file = Environment.getExternalStorageDirectory().getAbsolutePath();
//        mydir = new File(file+"/myDiary");
//
        date.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {



                file = Integer.toString(year) + "_" + Integer.toString(month+1) +"_"
                        + Integer.toString(day) +".txt";
                String str = readDiary(file);
                edit.setText(str);
                btn.setEnabled(true);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try{
//                    FileOutputStream fos = openFileOutput(file, Context.MODE_PRIVATE);
//                    String str = edit.getText().toString();
//                    fos.write(str.getBytes());
//                    fos.close();
//                    Toast.makeText(getApplicationContext(),file+" 이 저장됨",Toast.LENGTH_SHORT).show();
//                }catch (IOException e){
//
//                }

                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO myDiary VALUES ('"+date.getYear()+
                        "',"+edit.getText().toString()+");");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"저장됨",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public String readDiary(String fName){
        String diaryStr = null;

        try{
            FileInputStream fis = openFileInput(fName);
            byte[] txt = new byte[500];
            fis.read(txt);
            fis.close();
            diaryStr = (new String(txt)).trim();
            btn.setText("수정하기");
        }catch (IOException e){
            edit.setHint("일기 없음");
            btn.setText("새로 저장");
        }
        return diaryStr;
    }

    public class myDBHelper extends SQLiteOpenHelper {

        public myDBHelper(Context context) {
            super(context, "MyDB.db", null, 1);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE myDiary(diaryDate char(10),content varchar(500));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS myDiary");
            onCreate(db);
        }


    }

}
