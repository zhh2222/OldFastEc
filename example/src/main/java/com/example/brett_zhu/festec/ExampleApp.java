package com.example.brett_zhu.festec;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;

import com.example.brett_zhu.festec.event.ShareEvent;
import com.example.zhh.ec.database.DatabaseManager;
import com.example.zhh.ec.icon.FontEcModule;
import com.example.zhh_core.app.Zhh;
import com.example.brett_zhu.festec.event.TestEvent;
import com.example.zhh_core.net.interceptors.DebugInterceptor;
import com.example.zhh_core.net.rx.AddCookieInterceptor;
import com.example.zhh_core.util.callbacks.CallbackManager;
import com.example.zhh_core.util.callbacks.CallbackType;
import com.example.zhh_core.util.callbacks.IGlobalCallback;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mob.MobSDK;

import cn.jpush.android.api.JPushInterface;

/**
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
                withInterceptor(new DebugInterceptor("test", R.raw.test)).
                withWeChatAppId("wxfcdcecd9df8e0faa")
                .withWeChatAppSecret("a0560f75335b06e3ebea70f29ff219bf")
                .withJavascriptInterface("zhh")
                .withWebEvent("test", new TestEvent())
                .withWebEvent("share",new ShareEvent())
                .withWebHost("https://www.baidu.com/")
                //添加Cookie同步拦截器
                .withInterceptor(new AddCookieInterceptor())
                .configure();
//        initStetho();
        DatabaseManager.getInstance().init(this);
        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);


        MobSDK.init(this);

        CallbackManager.getInstance().addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
            @Override
            public void executeCallback(@Nullable Object args) {
                if (JPushInterface.isPushStopped(Zhh.getApplicationContext())) {
                    //开启极光推送
                    JPushInterface.setDebugMode(true);
                    JPushInterface.init(Zhh.getApplicationContext());
                }
            }
        }).addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
            @Override
            public void executeCallback(@Nullable Object args) {
                if (!JPushInterface.isPushStopped(Zhh.getApplicationContext())) {
                    JPushInterface.stopPush(Zhh.getApplicationContext());
                }
            }
        });
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
