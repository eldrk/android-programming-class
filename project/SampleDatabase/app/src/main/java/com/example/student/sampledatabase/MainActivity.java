package com.example.student.sampledatabase;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    Button createDataBtn,createTableBtn,insertBtn,updateBtn,deleteBtn;
    EditText databaseEt,tableNameEt,insertEt,updateEt,deleteEt;
    TextView tv;
    SQLiteDatabase db;
    String databaseName;
    String tableName;
    boolean databaseCreated;
    boolean tableCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDataBtn = (Button) findViewById(R.id.createDataBtn);
        createTableBtn = (Button) findViewById(R.id.createTableBtn);
        insertBtn = (Button) findViewById(R.id.insertBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        databaseEt = (EditText) findViewById(R.id.databaseEt);
        tableNameEt = (EditText) findViewById(R.id.tableNameEt);
        insertEt = (EditText) findViewById(R.id.insertEt);
        updateEt = (EditText) findViewById(R.id.updateEt);
        deleteEt = (EditText) findViewById(R.id.deleteEt);
        tv = (TextView) findViewById(R.id.tv);

        createDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseName = databaseEt.getText().toString();
                createDatabase(databaseName);
            }
        });

        createTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableName = tableNameEt.getText().toString();
                createTable(tableName);
                int count = insertRecord(tableName);
                println(count + "records inserted.");
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableName = insertEt.getText().toString();
                insertRecordParam(tableName);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tableName = updateEt.getText().toString();
                udateRecordParam(tableName);
            }
        });


    }


    private void createDatabase(String name){
        println("creating database [" + name + "].");

        try {
            db = openOrCreateDatabase(
                    name,
                    Activity.MODE_PRIVATE,
                    null);

            databaseCreated = true;
            println("database is created.");
        } catch(Exception ex) {
            ex.printStackTrace();
            println("database is not created.");
        }
    }

    private void createTable(String name){
        println("creating table [" + name + "].");
        db.execSQL("create table " + name + "("
                                   +"_id integer PRIMARY KEY autoincrement,"
                                   +" name text, "
                                   +" age integer, "
                                   +" phone text);");
        tableCreated = true;
    }

    private  int insertRecord(String name){
        println("inserting records into table " + name + ".");
        int count = 3;
        db.execSQL( "insert into " + name + "(name, age, phone) values ('John', 36, '010-7788-1234');" );
        db.execSQL( "insert into " + name + "(name, age, phone) values ('Mike', 35, '010-8888-1111');" );
        db.execSQL( "insert into " + name + "(name, age, phone) values ('Sean', 38, '010-6677-4321');" );

        return count;
    }

    private void println(String msg) {
        Log.d("MainActivity", msg);
        tv.append("\n" + msg);

    }

    private int insertRecordParam(String name){
        println("inserting records using parameters.");
        int count = 1;

        ContentValues recordValues = new ContentValues();

        recordValues.put("name" , "Rice");

        recordValues.put("age",43);
        recordValues.put("phone","010-3322-9811");

        int rowPosition = (int)db.insert(name,null,recordValues);
        return count;
    }

    private int udateRecordParam(String name){
        println("updating records using parameters.");

        ContentValues recordValues = new ContentValues();
        recordValues.put("age",43);
        String[] whereArgs = {"Mike"};
        int rowAffected = db.update("employee",recordValues,"name=?",whereArgs);

        return rowAffected;
    }

    private int deleteRecordParam(String name){
        println("deleting records using parameters.");

        String[] whereArgs = {"Rice"};

        int rowAffected = db.delete(name,"name = ? ", whereArgs);
        return  rowAffected;
    }



}
