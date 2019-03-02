package com.example.zhh_core.net;

import android.content.Context;

import com.example.zhh_core.net.callback.IError;
import com.example.zhh_core.net.callback.IFailure;
import com.example.zhh_core.net.callback.IRequest;
import com.example.zhh_core.net.callback.ISuccess;
import com.example.zhh_core.ui.LoaderStyle;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author brett-zhu
 * created at 2019/3/2 14:41
 */
public class RestClientBuilder {
    private String mUrl;
    private static WeakHashMap<String, Object> mParams = RestCreator.getParams();
    private IRequest mRequest;
    private ISuccess mSuccess;
    private IError mError;
    private IFailure mFailure;
    private RequestBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        mParams.put(key,value);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest ) {
        this.mRequest = iRequest;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mSuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mError = iError;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClient build(){
        return new RestClient(mUrl, mParams, mRequest, mSuccess, mError, mFailure, mBody,mContext,mLoaderStyle);
    }

}
