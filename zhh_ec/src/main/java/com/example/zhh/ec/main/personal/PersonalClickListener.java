package com.example.zhh.ec.main.personal;

import android.view.PointerIcon;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.zhh.ec.main.personal.list.ListBean;
import com.example.zhh_core.delegates.ZhhDelegate;

/**
 * @author brett-zhu
 * created at 2019/3/17 22:57
 */
public class PersonalClickListener extends SimpleClickListener {

    private final ZhhDelegate DELEGATE;

    public PersonalClickListener(ZhhDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(i);
        int id = bean.getId();
        switch (id) {
            case 1:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
                break;
            case 2:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
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
