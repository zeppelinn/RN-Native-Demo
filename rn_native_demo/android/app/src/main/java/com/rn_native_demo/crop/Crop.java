package com.rn_native_demo.crop;

import com.facebook.react.bridge.Promise;

/**
 * Created by lijun on 2018/10/2.
 */

public interface Crop {
    void selectWithCrop(int aspectX, int aspectY, Promise promise);
}
