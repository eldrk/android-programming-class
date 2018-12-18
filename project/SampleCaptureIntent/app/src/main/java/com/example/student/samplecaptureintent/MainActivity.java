package com.example.student.samplecaptureintent;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_IMAGE_CAPTURE = 1001;
    ImageView iv;

    File file=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        iv = (ImageView)findViewById(R.id.iv);

        try {
            file = createFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }

    public void onBtnClicked(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
        }
    }

    private File createFile() throws IOException{
        String imageFileName = "test.jpg";
        File storageDir = Environment.getExternalStorageDirectory();
        File curFile = new File(storageDir,imageFileName);

        return curFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode ==RESULT_OK){

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            if (file != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

                iv.setImageBitmap(bitmap);
            } else {
                //Toast.makeText(getApplicationContext(), "File is null.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
