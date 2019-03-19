package com.example.zhh.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.zhh.ec.detail.GoodsDetailDelegate;
import com.example.zhh_core.delegates.ZhhDelegate;
import com.example.zhh_ui.recycler.MultipleFields;
import com.example.zhh_ui.recycler.MultipleItemEntity;

/**
 * @author brett-zhu
 * created at 2019/3/10 11:24
 */
public class IndexItemClickListener extends SimpleClickListener {

    private final ZhhDelegate DELEGATE;

    private IndexItemClickListener(ZhhDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static IndexItemClickListener create(ZhhDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final int goodsId = entity.getField(MultipleFields.ID);
        final GoodsDetailDelegate detailDelegate = GoodsDetailDelegate.create(goodsId);
        DELEGATE.getSupportDelegate().start(detailDelegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
