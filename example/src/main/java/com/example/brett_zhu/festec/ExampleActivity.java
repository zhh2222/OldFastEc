package com.example.brett_zhu.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.example.zhh.ec.launcher.LauncherDelegate;
import com.example.zhh.ec.sign.ISignListener;
import com.example.zhh.ec.sign.SignInDelegate;
import com.example.zhh.ec.sign.SignUpDelegate;
import com.example.zhh_core.activities.ProxyActivity;
import com.example.zhh_core.delegates.ZhhDelegate;
import com.example.zhh_core.ui.launcher.ILauncherListener;
import com.example.zhh_core.ui.launcher.OnLauncherFinishTag;

/**
 * @author brett-zhu
 * created at 2019/3/1 19:51
 */
public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener {

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
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this,"启动结束，用户登录了",Toast.LENGTH_LONG).show();
                startWithPop(new ExampleDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this,"启动结束，用户没登录",Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
