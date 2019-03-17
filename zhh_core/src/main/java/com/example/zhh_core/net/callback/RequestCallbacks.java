package com.example.zhh_core.net.callback;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.example.zhh_core.ui.loader.LoaderStyle;
import com.example.zhh_core.ui.loader.ZhhLoader;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author brett-zhu
 * created at 2019/3/2 22:09
 */
public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest request,
                            ISuccess success,
                            IError error,
                            IFailure failure,
                            LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        stopLoading();

    }

    @Override
    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void stopLoading() {
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ZhhLoader.stopLoading();
                }
            }, 1000);
        }
    }
}
