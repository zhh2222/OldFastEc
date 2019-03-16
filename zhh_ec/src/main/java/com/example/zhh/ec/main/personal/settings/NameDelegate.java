package com.example.zhh.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.zhh.ec.R;
import com.example.zhh_core.delegates.ZhhDelegate;

/**
 * @author brett-zhu
 * created at 2019/3/16 16:14
 */
public class NameDelegate extends ZhhDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void enqueueAction(Runnable runnable) {

    }

}
