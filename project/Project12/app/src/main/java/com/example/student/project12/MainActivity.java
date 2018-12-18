package com.example.student.project12;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    myDBHelper myHelper;
    EditText et1,et2;
    TextView text1,text2;
    Button btnInit,btnInsert,btnSelect,btnUpdate,btnDelete;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);

        btnInit = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        myHelper = new myDBHelper(this);
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB,1,2);
                sqlDB.close();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO sample VALUES ('"+et1.getText().toString()+
                        "',"+et2.getText().toString()+");");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"입력됨",Toast.LENGTH_SHORT).show();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM sample;",null);

                String sName = "이름" +"\r\n" +"─────────" +"\r\n";
                String sNumber = "인원" +"\r\n" +"─────────" +"\r\n";

                while (cursor.moveToNext()){
                    sName += cursor.getString(0)+"\r\n";
                    sNumber += cursor.getString(1)+"\r\n";
                }

                text1.setText(sName);
                text2.setText(sNumber);

                cursor.close();
                sqlDB.close();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("DELETE FROM sample WHERE sName = '" +et1.getText().toString()+"';");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"삭제됨",Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("UPDATE sample SET sNumber = "+et2.getText().toString()+ " WHERE sName = '" +et1.getText().toString()+"';");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"수정됨",Toast.LENGTH_SHORT).show();
            }

        });
    }

    public class myDBHelper extends SQLiteOpenHelper{

        public myDBHelper(Context context){
            super(context,"sample.db",null,1);
        }



        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE test(tName char(10),tNumber INT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS test");
            onCreate(db);
        }



    }
}
