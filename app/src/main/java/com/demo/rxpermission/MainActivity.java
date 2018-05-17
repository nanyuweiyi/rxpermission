package com.demo.rxpermission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.nanyuweiyi.rxpermission.Permission;
import com.nanyuweiyi.rxpermission.RxPermissions;

import io.reactivex.functions.Consumer;

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

        /*rxPermissions.request(Manifest.permission.CALL_PHONE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if(granted){
                    Toast.makeText(MainActivity.this, "权限OK", Toast.LENGTH_SHORT).show();
                    callTel("10086");
                }else{
                    Toast.makeText(MainActivity.this, "缺少权限", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        rxPermissions.requestEach(Manifest.permission.CALL_PHONE).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted) {
                    Toast.makeText(MainActivity.this, "权限OK", Toast.LENGTH_SHORT).show();
                    callTel("10086");
                } else if (permission.shouldShowRequestPermissionRationale) {
                    // Denied permission without ask never again
                    Toast.makeText(MainActivity.this, "权限禁止，会继续询问", Toast.LENGTH_SHORT).show();
                } else {
                    // Denied permission with ask never again
                    // Need to go to the settings
                    Toast.makeText(MainActivity.this, "权限禁止，不会继续询问", Toast.LENGTH_SHORT).show();
                            /*//跳转系统设置界面 （有些手机不能跳到该设置界面）
                            Intent intent = new Intent();
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                            intent.setData(Uri.fromParts("package", getPackageName(), null));
                            startActivity(intent);*/
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
