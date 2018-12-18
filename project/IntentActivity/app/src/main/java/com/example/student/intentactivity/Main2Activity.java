package com.example.student.intentactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    Button btn1;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         btn1 = (Button)findViewById(R.id.btn1);

         intent = new Intent(Main2Activity.this,Main3Activity.class);
         intent.putExtra("int_value",1234);
         intent.putExtra("str_value","intent");
         intent.putExtra("boolean_value",true);
         intent.putExtra("double_value",3.14d);
         intent.putExtra("float_value",42.195f);

        int[] int_arr = {1,2,3};
        boolean[] bool_arr = {true, false, true};
        double[] double_arr = {4.4d, 5.5d, 6.6d};
        float[] float_arr = {1.1f, 2.2f, 3.3f};

        intent.putExtra("int_arr", int_arr);
        intent.putExtra("bool_arr", bool_arr);
        intent.putExtra("double_arr", double_arr);
        intent.putExtra("float_arr", float_arr);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }
}
