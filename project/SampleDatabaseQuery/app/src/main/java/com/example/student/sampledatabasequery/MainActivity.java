package com.example.student.sampledatabasequery;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private static String DATABASE_NAME = null;
    private static String TABLE_NAME = "employee";
    private static int DATABASE_VERSION = 1;

    private DatabaseHelper dbHelper;
    private  SQLiteDatabase db;

    Button btn;
    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        et = (EditText) findViewById(R.id.et);
        tv = (TextView) findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DATABASE_NAME = et.getText().toString();

                boolean isOpen = openDatabase();
                if(isOpen){
                    executeRawQuery();
                    executeRawParam();
                }
            }
        });
    }

    private boolean openDatabase(){
        println("opening database [" +DATABASE_NAME+"].");

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        return true;
    }

    private void executeRawQuery(){

        println("\nexecuteRawQuery called.\n");
        Cursor c1 = db.rawQuery("select count(*) as Total from " +TABLE_NAME,null);
        println("cursor count : " +c1.getCount());
        c1.moveToNext();
        println("record count : "+c1.getInt(0));

        c1.close();

    }

    private void executeRawParam(){

        println("\nexecuteRawQueryParam called.\n");
        String SQL = "select name, age, phone" + " from " + TABLE_NAME + " where age > ?";
        String[] args = {"30"};

        Cursor c1 = db.rawQuery(SQL,args);
        int recordCount = c1.getCount();
        println("cursor count : " + recordCount + "\n");

        for(int i = 0; i<recordCount; i++){
            c1.moveToNext();
            String name = c1.getString(0);
            int age = c1.getInt(1);
            String phone = c1.getString(2);

            println("Record #" +i+ " : " +name+ ", " + age + ", " +phone);
        }

        c1.close();

    }

    private void println(String msg) {
        Log.d(TAG, msg);
        tv.append("\n" + msg);

    }


    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            println("opend database [" + DATABASE_NAME + "].");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            println("creating table [ " +TABLE_NAME + "].");
            try{

            }catch (Exception e){
                Log.e(TAG,"Exception in DROP_SQL",e);
            }

            String CREATE_SQL = "create table " + TABLE_NAME + "("
                    +"_id integer PRIMARY KEY autoincrement,"
                    +" name text, "
                    +" age integer, "
                    +" phone text)";

            try{
                db.execSQL(CREATE_SQL);
            }catch (Exception e){
                Log.e(TAG,"Exception in CREATE_SQL",e);
            }

            println("inserting records.");

            try{
                db.execSQL( "insert into " + TABLE_NAME + "(name, age, phone) values ('John', 36, '010-7788-1234');" );
                db.execSQL( "insert into " + TABLE_NAME + "(name, age, phone) values ('Mike', 35, '010-8888-1111');" );
                db.execSQL( "insert into " + TABLE_NAME + "(name, age, phone) values ('Sean', 38, '010-6677-4321');" );
            }catch (Exception e){
                Log.e(TAG,"Exception in insert SQL",e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            Log.w(TAG, "Upgrading database from version " +i + "to" +i1+".");
        }
    }
}
