package com.example.student.project6_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] items = {"CSI-뉴욕", "CSI-라스베가스", "CSI-마이애미", "Friends", "Fringe", "Lost"};

        AutoCompleteTextView auto = (AutoCompleteTextView)findViewById(R.id.auto);
        MultiAutoCompleteTextView multiauto = (MultiAutoCompleteTextView)findViewById(R.id.multiauto);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,items);
        auto.setAdapter(adapter);

        MultiAutoCompleteTextView.CommaTokenizer token = new MultiAutoCompleteTextView.CommaTokenizer();

        multiauto.setTokenizer(token);
        multiauto.setAdapter(adapter);
    }
}
