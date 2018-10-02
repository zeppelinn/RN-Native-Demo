package com.rn_native_demo.crop;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by lijun on 2018/10/2.
 */

public class ImageCropModule extends ReactContextBaseJavaModule implements Crop {

    private CropImpl cropImpl;

    public ImageCropModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    //ReactContextBaseJavaModule
    //ReactNative通过该方法获取原生模块的名称
    @Override
    public String getName() {
        return "ImageCrop";
    }

    private CropImpl getCrop(){
        if (this.cropImpl == null){
            cropImpl = CropImpl.of(getCurrentActivity());
            getReactApplicationContext().addActivityEventListener(cropImpl);
        }else{
            cropImpl.refreshActivity(getCurrentActivity());
        }
        return cropImpl;
    }


    /**
     * 实现JS向原生接口传递数据
     * 添加ReactMethod注解才可以让RN调用该接口
     *
     * 注1:Java与JS中变量类型的对照
     *  Java             JS
     *
     * Boolean       -> Bool
     * Integer       -> Number
     * Double        -> Number
     * Float         -> Number
     * String        -> String
     * Callback      -> function
     * ReadableMap   -> Object
     * ReadableArray -> Array
     *
     * 注2:在任何情况下 js和原生模块之间的数据通信都是异步进行的
     *
     * 注3:除了下面使用的Promise作为回调参数，RN还提供了Callback作为回调方法
     *     如果使用Callback替代Promise，那么参数如下：
     *     selectWithCrop(int aspectX, int aspectY, Callback onErrorCallback, Callback onSuccessCallback)
     *     getCrop().selectWithCrop在实现的时候也只需要将Promise的reject和resolve替换成上面的两个回调就行
     *
     * 注4:不论是使用Promise还是Callback，原生接口或者RN都只能调用一次
     *      想要解决发送多次消息的问题可以参考文章 https://blog.csdn.net/u014041033/article/details/50610939
     *      上面使用了RN提供给我们的消息处理模块DeviceEventManagerModule来实现跨线程通信
     * */
    @Override @ReactMethod
    public void selectWithCrop(int aspectX, int aspectY, Promise promise) {
        getCrop().selectWithCrop(aspectX, aspectY, promise);
    }
}
