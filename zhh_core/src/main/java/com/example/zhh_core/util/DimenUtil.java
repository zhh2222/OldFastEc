package com.example.zhh_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.zhh_core.app.Zhh;

/**
 * @author brett-zhu
 * created at 2019/3/2 22:51
 */
public class DimenUtil {
    public static int getScreenWidth() {
        final Resources resources = Zhh.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Zhh.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
