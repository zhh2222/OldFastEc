package com.example.zhh_core.ui.camera;

import android.net.Uri;

import com.example.zhh_core.delegates.PermissionCheckerDelegate;
import com.example.zhh_core.util.FileUtil;

/**
 * @author brett-zhu
 * created at 2019/3/16 16:44
 * 照相机调用类
 */
public class ZhhCamera {
    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }


}
