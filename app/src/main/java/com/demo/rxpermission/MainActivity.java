package com.demo.rxpermission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.nanyuweiyi.rxpermission_lib2.RxPermissions;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tvTel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPression();
            }
        });

    }

    void requestPression(){
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);

        rxPermissions.request(Manifest.permission.CALL_PHONE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if(granted){
                    callTel("10086");
                }else{
                    Toast.makeText(MainActivity.this, "缺少权限，无法操作", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void callTel(String callNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + callNum));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this, "该设备暂不支持拨打电话", Toast.LENGTH_SHORT).show();
        }
    }
}
