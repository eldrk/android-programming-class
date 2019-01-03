package com.example.student.bluethoothactivity;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.OutputStream;

/**
 * Created by student on 2018-12-26.
 */

public class MessageActivity extends AppCompatActivity {

    TextView tv_log;
    Button btn_send;
    EditText et_msg;
    BluetoothSocket connSocket;
    boolean bRead = true;
    Handler writeHandler;

    MsgThread msgThread;
    WriteThread writeThread;
    ReadThread readThread;
    StringBuffer sb;

    final int SEND_MESSAGE = 100;
    final int RECEIVED_MESSAGE = 200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tv_log = (TextView)findViewById(R.id.tv_log);
        btn_send = (Button)findViewById(R.id.btn_send);
        et_msg = (EditText)findViewById(R.id.et_msg);

        connSocket = MainActivity.connSocket;

        sb = new StringBuffer();

        msgThread = new MsgThread();
        msgThread.start();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String send_msg = et_msg.getText().toString();
                if(writeHandler != null){
                    Message msg = new Message();
                    msg.obj = send_msg;
                    writeHandler.sendMessage(msg);
                }
            }
        });
    }

    class MsgThread extends Thread{
        @Override
        public void run() {
            try{
                if(readThread != null){
                    readThread.interrupt();
                }
                readThread = new ReadThread(connSocket);
                readThread.start();

                if(writeThread != null){

                    writeHandler.getLooper().quit();
                }

                writeThread = new WriteThread(connSocket);
                writeThread.start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    Handler msgHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what) {
                case SEND_MESSAGE:
                    sb.append("me > "+(String)msg.obj);
                    tv_log.setText(sb);
                    break;
                case RECEIVED_MESSAGE:
                    sb.append("you > " + (String)msg.obj);
                    tv_log.setText(sb);
                    break;
            }
        }
    };

    class WriteThread extends Thread {
        BluetoothSocket socket;
        OutputStream os = null;

        public WriteThread(BluetoothSocket socket) {
            // 통신을 위한 bluetoothSocket 객체를 받는다.
            this.socket = socket;
            try {
                // bluetootsocket객체어서 OutputStream을 생성한다.
                os = socket.getOutputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            Looper.prepare();
            // 메시지를 받으면, 처리하는 핸들러
            writeHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    try {
                        // 주어진 데이터를 OutputStream에 전달하여 상대측에 송신한다.
                        os.write(((String)msg.obj).getBytes());
                        os.flush();

                        // 전송한 데이터를 MessageActivity안의 TextView에 출력하기 위해 메시지를 전달한다.
                        Message msg_to_acti = new Message();
                        msg_to_acti.what = 200;
                        msg_to_acti.obj = msg.obj;
                        msgHandler.sendMessage(msg_to_acti);
                    } catch (Exception e) {
                        e.printStackTrace();
                        writeHandler.getLooper().quit();
                    }
                }
            };

            Looper.loop();
        }
    }


    class ReadThread extends Thread{
        BluetoothSocket socket;
        BufferedInputStream bis = null;

        public ReadThread(BluetoothSocket socket) {
            this.socket = socket;
            try {
                // bluetoothSocket에서 bufferedInputStream을 생성한다.
                bis = new BufferedInputStream(
                        socket.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted() && bRead) {
                try {
                    // 데이터를 임시로 저장할 버퍼를 만든다.
                    byte[] buf = new byte[1024];
                    // 버퍼에 데이터를 읽어온다.
                    int bytes = bis.read(buf);
                    // 읽어온 문자열 형태로 저장한다.
                    String read_str = new String(buf, 0, bytes);

                    // 읽어온 MessageActivity 안의 listview에 적용하기 위해 헨들러에 메시지를 전달한다
                    Message msg = new Message();
                    msg.what = 100;
                    msg.obj = read_str;
                    msgHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    bRead = false;
                }
            }
        }
    }
}
