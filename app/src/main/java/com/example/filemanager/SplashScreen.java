package com.example.filemanager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SplashScreen extends Activity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashfile);

        handler=new Handler();
        if(chkpermission()) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent intent = new Intent(SplashScreen.this, ListActivity.class);
                    String p = Environment.getExternalStorageDirectory().getPath();
                    intent.putExtra("path",p);
                    startActivity(intent);
                    finish();
            }
        },1000);
        }else
            reqpermission();
    }
    private boolean chkpermission(){
        int r=ContextCompat.checkSelfPermission(SplashScreen.this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(r== PackageManager.PERMISSION_GRANTED){
            return true;
        }else
            return false;
    }

    private void reqpermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(SplashScreen.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(SplashScreen.this,"Storage permission is required",Toast.LENGTH_SHORT).show();
        }else
        ActivityCompat.requestPermissions(SplashScreen.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},111);
    }
}
