package com.example.zhh.ec.main.personal.settings;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.zhh.ec.main.personal.list.ListBean;
import com.example.zhh_core.delegates.ZhhDelegate;

/**
 * @author brett-zhu
 * created at 2019/3/18 11:20
 */
public class SettingsClickListener extends SimpleClickListener {

    private final ZhhDelegate DELEGATE;

    public SettingsClickListener(ZhhDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(i);
        int id = bean.getId();
        switch (id) {
            case 1:
                //这是消息推送的开关
                break;
            case 2:
                DELEGATE.getSupportDelegate().start(bean.getDelegate());
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }
}
