package com.example.brett_zhu.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.zhh_core.delegates.ZhhDelegate;
import com.example.zhh_core.net.RestClient;
import com.example.zhh_core.net.callback.IFailure;

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
//        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder().
                url("https://news.baidu.com").
                loader(getContext()).
                success(response -> Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show()).failure(new IFailure() {
            @Override
            public void onFailure() {
                Toast.makeText(getContext(),"失败",Toast.LENGTH_LONG).show();
            }
        }).error((code, msg) -> {
            Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
        }).build().get();

    }

    @Override
    public void enqueueAction(Runnable runnable) {

    }
}
