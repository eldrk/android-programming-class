package com.example.student.project11_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> midList = new ArrayList<>();
        ListView lv = findViewById(R.id.lv);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,midList);
        lv.setAdapter(adapter);

        final EditText edit = findViewById(R.id.edit);
        Button btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midList.add(edit.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                midList.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
