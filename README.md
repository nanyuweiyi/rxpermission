# 使用说明

1、在project级build.gradle中添加： maven { url 'https://jitpack.io' }  

2、在app级build.gradle中添加：compile 'com.github.nanyuweiyi:rxpermission:1.3' 

3、使用demo:

```
    void requestPression(){
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CALL_PHONE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if(granted){
                      Toast.makeText(MainActivity.this, "权限OK", Toast.LENGTH_SHORT).show();
                      callTel("10086");
                }else{
                       Toast.makeText(MainActivity.this, "缺少权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
 ```
