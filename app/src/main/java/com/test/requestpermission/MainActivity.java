package com.test.requestpermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int STORAGE_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonRequest = findViewById(R.id.button);
        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //檢查權限，判斷權限是否已取得
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    //已取得權限
                    Toast.makeText(MainActivity.this, "You have already granted this permission!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    requestStoragePermission();
                }
            }
        });
    }

    //取得權限
    private void requestStoragePermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    //同意
                    .setPositiveButton("ok", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    //取得權限
                                    ActivityCompat.requestPermissions(MainActivity.this,
                                            new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                                    // AppConstant.LOCATION_REQUEST_CODE 為自己隨意定義的 int（例如：999）
                                }
                            })
                    //拒絕
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
        // 因為這個 method 會帶回任何權限視窗的結果，所以我們要用 requestCode 來判斷這是哪一個權限
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // 不等於 PERMISSION_GRANTED 代表被按下拒絕
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
                //沒有權限
                if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    //偵測使用者是否勾選「不要再問我」選項
                    openAppSettingsIntent();
                }
            } else {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
                //許可授予
            }
        }
    }
    //前往系統設定頁面
    private void openAppSettingsIntent() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}