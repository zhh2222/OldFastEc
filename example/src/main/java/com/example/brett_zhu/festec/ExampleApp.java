package com.example.brett_zhu.festec;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.zhh.ec.database.DatabaseManager;
import com.example.zhh.ec.icon.FontEcModule;
import com.example.zhh_core.app.Zhh;
import com.example.zhh_core.net.interceptors.DebugInterceptor;
import com.facebook.stetho.Stetho;
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
                withLoaderDelayed(1000).
                withApiHost("http://192.168.191.1:8080/").
                withInterceptor(new DebugInterceptor("test",R.raw.test)).
                withWeChatAppId("wxfcdcecd9df8e0faa")
                .withWeChatAppSecret("a0560f75335b06e3ebea70f29ff219bf")
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
