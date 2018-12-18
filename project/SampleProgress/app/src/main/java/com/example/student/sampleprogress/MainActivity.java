package com.example.student.sampleprogress;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int bringhtness =100;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar pb = (ProgressBar)findViewById(R.id.pb);
        
        ImageView iv = findViewById(R.id.iv);
        TextView tv = findViewById(R.id.tv);
        pb.setIndeterminate(false);
        pb.setMax(100);
        pb.setProgress(80);

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new ProgressDialog(getApplicationContext());
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("데이터를 확인하는 중입니다.");
                dialog.show();
            }
        });

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialog != null){
                    dialog.dismiss();
                }
            }
        });
    }
}
