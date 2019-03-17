package com.example.zhh.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zhh.ec.R;
import com.example.zhh.ec.R2;
import com.example.zhh.ec.main.sort.SortDelegate;
import com.example.zhh_core.delegates.ZhhDelegate;
import com.example.zhh_core.net.RestClient;
import com.example.zhh_core.net.callback.ISuccess;
import com.example.zhh_ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * @author brett-zhu
 * created at 2019/3/10 15:49
 */
public class VerticalListDelegate extends ZhhDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
    }
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void enqueueAction(Runnable runnable) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder().url("sort_list.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleItemEntity> data = new VerticalListDataConverter()
                                .setJsonData(response).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                }).build().get();
    }
}
