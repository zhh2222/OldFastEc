package com.example.brett_zhu.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.zhh_core.delegates.ZhhDelegate;
import com.example.zhh_core.net.RestClient;
import com.example.zhh_core.net.callback.IError;
import com.example.zhh_core.net.callback.IFailure;
import com.example.zhh_core.net.callback.ISuccess;

/**
 * @author brett-zhu
 * created at 2019/3/2 13:22
 */
public class ExampleDelegate extends ZhhDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder().url("https://news.baidu.com/").loader(getContext()).
                success(response -> Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show()).failure(new IFailure() {
            @Override
            public void onFailure() {

            }
        }).error((code, msg) -> {

        }).build().get();

    }
}
