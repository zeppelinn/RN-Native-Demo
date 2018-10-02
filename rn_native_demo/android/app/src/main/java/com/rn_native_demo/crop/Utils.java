package com.rn_native_demo.crop;

import android.os.Environment;

import java.io.File;

/**
 * Created by lijun on 2018/10/2.
 */

public class Utils {
    /**
     * 获取一个临时文件
     * @param fileName
     * @return
     */
    public static File getPhotoCacheDir(String fileName) {
        return new File(Environment.getExternalStorageDirectory().getAbsoluteFile(),fileName);
    }
}
