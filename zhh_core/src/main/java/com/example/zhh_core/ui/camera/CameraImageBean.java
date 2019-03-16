package com.example.zhh_core.ui.camera;

import android.net.Uri;

/**
 * @author brett-zhu
 * created at 2019/3/16 16:45
 * 存储一些中间值
 */
public final class CameraImageBean {
    private Uri mPath = null;
    private static final class Holder{
        private static final CameraImageBean INSTANCE = new CameraImageBean();
    }

    public static CameraImageBean getInstance() {
        return Holder.INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri path) {
        mPath = path;
    }
}
