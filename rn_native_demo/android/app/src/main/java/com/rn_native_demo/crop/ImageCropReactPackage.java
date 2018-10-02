package com.rn_native_demo.crop;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lijun on 2018/10/2.
 * 该模块负责注册和导出android原生模块
 */

public class ImageCropReactPackage implements ReactPackage {
    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        //将之前创建的ImageCropModule模块导入
        //注册之后需要在MainApplication.java中注册ImageCropReactPackage
        modules.add(new ImageCropModule(reactContext));
        return modules;
    }
}
