package com.example.student.permissionactivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean permission_granted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int check1 = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECEIVE_SMS);
        int check2 = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
        int check3 = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CHANGE_WIFI_STATE);

        if(check1 == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(),"SMS 수신 권한 있음",Toast.LENGTH_SHORT).show();
            permission_granted = true;
        }

        if(check2 == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(),"camera 권한 있음",Toast.LENGTH_SHORT).show();
            permission_granted = true;
        }

        if(check3 == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(),"wifi 권한 있음",Toast.LENGTH_SHORT).show();
            permission_granted = true;
        }

        else{
            if(permission_granted == false){
                Toast.makeText(getApplicationContext(),"SMS 수신 권한 없음",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"camera 권한 없음",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"wifi 권한 없음",Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_WIFI_STATE
                },1);
            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,
                            "SMS 권한을 사용자가 승인함",
                            Toast.LENGTH_LONG).show();

                }if(grantResults.length > 0 &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this,
                            "Camera 권한을 사용자가 승인함",
                            Toast.LENGTH_LONG).show();

                }if(grantResults.length > 0 &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                     Toast.makeText(MainActivity.this,
                        "WiFi 권한을 사용자가 승인함",
                        Toast.LENGTH_LONG).show();
                }

                else {
                    Toast.makeText(MainActivity.this,
                            "SMS 권한을 사용자가 거부함",
                            Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this,
                            "WiFi 권한을 사용자가 거부함",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
