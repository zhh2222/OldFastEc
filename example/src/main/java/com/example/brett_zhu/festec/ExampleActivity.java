package com.example.brett_zhu.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.example.zhh.ec.launcher.LauncherDelegate;
import com.example.zhh.ec.sign.SignUpDelegate;
import com.example.zhh_core.activities.ProxyActivity;
import com.example.zhh_core.delegates.ZhhDelegate;

/**
 *
 * @author brett-zhu
 * created at 2019/3/1 19:51
 */
public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public ZhhDelegate setRootDelegate() {
        return new SignUpDelegate();
    }

}
