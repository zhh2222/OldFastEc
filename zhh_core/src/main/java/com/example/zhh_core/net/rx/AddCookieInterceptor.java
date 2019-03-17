package com.example.zhh_core.net.rx;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.example.zhh_core.net.util.ZhhPreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author brett-zhu
 * created at 2019/3/13 20:32
 */
public final class AddCookieInterceptor implements Interceptor {
    @SuppressLint("CheckResult")
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable.just(ZhhPreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String cookie) throws Exception {
                        //给原生API请求附带上WebView拦截下来的Cookie
                        builder.addHeader("Cookie", cookie);
                    }
                });
        return chain.proceed(builder.build());
    }
}
