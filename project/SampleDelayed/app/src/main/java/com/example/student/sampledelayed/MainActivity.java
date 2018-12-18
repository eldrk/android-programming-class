package com.example.student.sampledelayed;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });
    }

    private void request(){
        String title="원격 요청";
        String message = "데이터를 요청하시겠습니까?";
        String btnYes = "예";
        String btnNo = "아니요";

        AlertDialog dialog = makeRequestDialog(title,message,btnYes,btnNo);
        dialog.show();

        tv.setText("원격 데이터 요청 중 ...");
    }

    private AlertDialog makeRequestDialog(CharSequence title, CharSequence message,
                                          CharSequence btnYes, CharSequence btnNo){
        AlertDialog.Builder requestDialog = new AlertDialog.Builder(this);
        requestDialog.setTitle(title);
        requestDialog.setMessage(message);

        requestDialog.setPositiveButton(btnYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RequestHandler handler = new RequestHandler();
                handler.sendEmptyMessageDelayed(0,20);
            }
        });

        requestDialog.setNegativeButton(btnNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        return requestDialog.show();
    }

    class RequestHandler extends Handler{
        public void handleMessage(Message msg){
            for(int k=0;k<10;k++){
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){}
            }
            tv.setText("원격 데이터 요청 완료");
        }
    }
}
