package com.example.brett_zhu.festec;

import android.app.Application;

import com.example.zhh.ec.icon.FontEcModule;
import com.example.zhh_core.app.Zhh;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * 
 * @author brett-zhu
 * created at 2019/3/1 19:52
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Zhh.init(this).
                withIcons(new FontAwesomeModule()).
                withIcons(new FontEcModule()).
                withApiHost("http://127.0.0.1/").configure();
    }
}
