package com.rn_native_demo.crop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;

/**
 * Created by lijun on 2018/10/2.
 */

public class CropImpl implements Crop, ActivityEventListener {

    private final int RC_PICK=50081;
    private final int RC_CROP=50082;
    private final String CODE_ERROR_PICK="用户取消";
    private final String CODE_ERROR_CROP="裁切失败";

    private Activity activity = null;

    private int aspectX = -1;
    private int aspectY = -1;
    private Promise pickPromise = null;
    private Uri outputUri = null;

    private CropImpl(Activity activity){
        this.activity = activity;
    }

    //向外提供该类的实例
    public static CropImpl of (Activity activity){
        return new CropImpl(activity);
    }

    //跳转相册后原先注册的activity会被销毁，需要刷新activity
    public void refreshActivity(Activity activity){
        this.activity = activity;
    }

    @Override
    public void selectWithCrop(int aspectX, int aspectY, Promise promise) {
        this.aspectX = aspectX;
        this.aspectY = aspectY;
        this.pickPromise = promise;
        this.activity.startActivityForResult(IntentUtils.getPickIntentWithGallery(),RC_PICK);
    }

    //ActivityEventListener
    //调用Activity.startActivityForResult会回调该方法
    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        //判断跳转意图
        if (requestCode == RC_PICK){//如果是从相册读取图片
            //读取成功
            if(resultCode == Activity.RESULT_OK && data != null){
                outputUri= Uri.fromFile(Utils.getPhotoCacheDir(System.currentTimeMillis()+".jpg"));
                onCrop(data.getData(), outputUri);
            }else{
                pickPromise.reject(CODE_ERROR_PICK, "获取图片失败");
            }
        }else if (requestCode == RC_CROP){
            if (resultCode == Activity.RESULT_OK){
                pickPromise.resolve(outputUri.getPath());
            }else{
                pickPromise.reject(CODE_ERROR_CROP, "图片裁剪失败");
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {}

    public void onCrop(Uri targetUri, Uri outputUri){
        this.activity.startActivityForResult(IntentUtils.getCropIntentWith(targetUri,outputUri,aspectX,aspectY),RC_CROP);
    }
}
