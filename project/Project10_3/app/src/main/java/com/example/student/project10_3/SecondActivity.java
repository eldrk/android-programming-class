package com.example.student.project10_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by student on 2018-11-09.
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("Second 액티비티");

        Intent intent = getIntent();
        final int hapValue = intent.getIntExtra("num1",0)
                +intent.getIntExtra("num2",0);
        final int chaValue = intent.getIntExtra("num1",0)
                -intent.getIntExtra("num2",0);
        final int gobValue = intent.getIntExtra("num1",0)
                *intent.getIntExtra("num2",0);
        final int miusValue = intent.getIntExtra("num1",0)
                /intent.getIntExtra("num2",0);
        final int manugeValue = intent.getIntExtra("num1",0)
                %intent.getIntExtra("num2",0);

        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(getApplicationContext(),MainActivity.class);

                outIntent.putExtra("Hap",hapValue);
                setResult(RESULT_OK,outIntent);
                outIntent.putExtra("Cha",chaValue);
                setResult(RESULT_OK,outIntent);
                outIntent.putExtra("Gob",gobValue);
                setResult(RESULT_OK,outIntent);
                outIntent.putExtra("Minus",miusValue);
                setResult(RESULT_OK,outIntent);
                outIntent.putExtra("Namuge",manugeValue);
                setResult(RESULT_OK,outIntent);


                finish();
            }
        });
    }
}
