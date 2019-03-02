package com.example.brett_zhu.festec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhh_core.activities.ProxyActivity;
import com.example.zhh_core.delegates.ZhhDelegate;

/**
 *
 * @author brett-zhu
 * created at 2019/3/1 19:51
 */
public class ExampleActivity extends ProxyActivity {

    @Override
    public ZhhDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

}
