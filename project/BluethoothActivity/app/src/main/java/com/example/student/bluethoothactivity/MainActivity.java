package com.example.student.bluethoothactivity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;
    Button btn_scan,btn_dis;
    ArrayList<BluetoothDevice> device_list;
    boolean bPerm;
    ListView lv_list;
    DeviceAdapter deviceAdapter;
    boolean bConn = false;
    boolean bSelect = false;
    AlertDialog selectDialog;
    BluetoothServerSocket serverSocket;
    static BluetoothSocket connSocket;
    BluetoothDevice targetDevice;
    final UUID MY_UUID = UUID.fromString("00001111-1010-1010-1010-12345678ABCD");
    ServerThread serverThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPermission(new String[]{
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN
        });

        btn_dis = (Button)findViewById(R.id.btn_dis);
        btn_scan = (Button)findViewById(R.id.btn_scan);
        lv_list = (ListView)findViewById(R.id
                .lv_list);


        btn_dis.setOnClickListener(new BtnListener());
        btn_scan.setOnClickListener(new BtnListener());

        device_list = new ArrayList<BluetoothDevice>();
        deviceAdapter = new DeviceAdapter(MainActivity.this,R.layout.item_device,device_list);
        lv_list.setAdapter(deviceAdapter);

        lv_list.setOnItemClickListener(new ItemListner());

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter !=null){
            bluetoothAdapter.enable();
            Toast.makeText(MainActivity.this,"서버동작",Toast.LENGTH_SHORT).show();

            serverThread = new ServerThread();
            serverThread.start();
        }else{
            Toast.makeText(getApplicationContext(),"블루투스를 지원하지않음",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    class ItemListner implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
            targetDevice = device_list.get(i);
            Toast.makeText(MainActivity.this,"클라이언트동작",Toast.LENGTH_SHORT).show();
            ClientThread clientThread = new ClientThread();
            clientThread.start();
            serverThread.interrupt();
        }
    }

    class ServerThread extends Thread{

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                if(!bConn && bluetoothAdapter.isEnabled()){
                    try{
                        serverSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord("Bluetooth",MY_UUID);
                        connSocket = serverSocket.accept();

                        if(connSocket != null){
                            Intent intent = new Intent(MainActivity.this,MessageActivity.class);
                            startActivity(intent);
                            bConn =true;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else{
                    try{
                        Thread.sleep(500);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class ClientThread extends Thread{
        @Override
        public void run() {
            try{
                bluetoothAdapter.cancelDiscovery();
                connSocket = targetDevice.createInsecureRfcommSocketToServiceRecord(MY_UUID);
                connSocket.connect();
                bConn = true;

                Intent intent = new Intent(MainActivity.this,MessageActivity.class);
                startActivity(intent);


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    class BtnListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_dis:
                    Toast.makeText(getApplicationContext(),"다른 기기가 스마트폰을 검색하여 인지할수 있음",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,1000);
                    startActivity(intent);
                    break;
                case R.id.btn_scan:
                    Toast.makeText(getApplicationContext(),"페어링된 기기를 검색함",
                            Toast.LENGTH_SHORT).show();
                    Set<BluetoothDevice>devices = bluetoothAdapter.getBondedDevices();
                    device_list.clear();

                    if(devices.size()>0){
                        Iterator<BluetoothDevice> iter = devices.iterator();
                        while (iter.hasNext()){
                            BluetoothDevice d = iter.next();
                            device_list.add(d);
                            Log.d("BLUETEST","name : " +d.getName()+ " addr : "+d.getAddress());
                        }
                        deviceAdapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(getApplicationContext(),"검색된 기기가 없습니다.",Toast.LENGTH_SHORT).show();

                    }
                    break;
            }
        }
    }

    private void setPermission(String[] perm) {
        boolean bPerm = false;

        for (int i = 0; i < perm.length; i++) {
            if(ContextCompat.checkSelfPermission(getApplicationContext(), perm[i])
                    != PackageManager.PERMISSION_GRANTED) {
                bPerm = false;
            }
        }

        if(!bPerm) {
            ActivityCompat.requestPermissions(
                    this, perm, 200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean bPerm = true;
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 200 && grantResults.length > 0) {
            for(int i = 0; i < grantResults.length; i++) {
                if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    bPerm = false;
                }
            }
        }
        this.bPerm = bPerm;
    }
}
