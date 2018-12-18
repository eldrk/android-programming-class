package com.example.student.samplefocous;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String name;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showToast("onCreate 호출됨");

        et = (EditText) findViewById(R.id.et);
        Button btn =(Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = et.getText().toString();
                Toast.makeText(getApplicationContext(),"입력된 값을 변수에 저장했습니다 : " +name,Toast.LENGTH_LONG).show();
            }
        });

        if(savedInstanceState != null){
            name = savedInstanceState.getString("name");
            Toast.makeText(getApplicationContext(),"값을 복원했습니다 : " +name,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name",name);
    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        showToast("onStart 호출됨");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        showToast("onStop 호출됨");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        showToast("onDestroy 호출됨");
//    }

    public void showToast(String data){
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
    }
}
