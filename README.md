# 使用说明
1、在project级build.gradle中添加： maven { url 'https://jitpack.io' }  

2、在app级build.gradle中添加：compile 'com.github.nanyuweiyi:rxpermission:1.2' 

3、使用demo:

void requestPression(){
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);

        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if(granted){
                    Intent intent = new Intent(mContext, ChoosePicActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(TestActivity.this, "缺少权限，无法操作", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
