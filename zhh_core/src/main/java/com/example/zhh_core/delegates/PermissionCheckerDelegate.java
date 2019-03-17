package com.example.zhh_core.delegates;

import android.Manifest;

import com.example.zhh_core.ui.camera.ZhhCamera;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * @author brett-zhu
 * created at 2019/3/2 12:16
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate{
    //不是直接调用方法，所以不能有修饰词
    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera() {
        ZhhCamera.start(this);
    }

}
