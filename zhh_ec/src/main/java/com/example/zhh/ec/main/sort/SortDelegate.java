package com.example.zhh.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.zhh.ec.R;
import com.example.zhh_core.delegates.bottom.BottomItemDelegate;

/**
 * @author brett-zhu
 * created at 2019/3/9 10:14
 */
public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
