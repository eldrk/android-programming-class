package com.example.student.sampleparcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    TextView tv;

    public static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tv  = (TextView) findViewById(R.id.tv);
        Button btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name","woosung");
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        Intent intent = getIntent();
        processIntent(intent);
    }

    private void processIntent(Intent intent){
        if(intent !=null){
            Bundle bundle = intent.getExtras();

            SimpleData data = (SimpleData)bundle.getParcelable(KEY_SIMPLE_DATA);
            tv.setText("전달받은 데이터\nNumber : "+data.getNumber() + "\nMessage : " +data.getMessage());

        }
    }
}
