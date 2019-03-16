package com.example.zhh.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zhh.ec.R;
import com.example.zhh.ec.R2;
import com.example.zhh.ec.main.personal.PersonalDelegate;
import com.example.zhh_core.delegates.ZhhDelegate;
import com.example.zhh_core.net.RestClient;
import com.example.zhh_ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * @author brett-zhu
 * created at 2019/3/16 14:16
 */
public class OrderListDelegate extends ZhhDelegate {

    private String mType = null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mType = args.getString(PersonalDelegate.ORDER_TYPE);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("order_list.json")
                .params("type", mType)
                .success(response -> {
                    final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    mRecyclerView.setLayoutManager(manager);
                    final List<MultipleItemEntity> data = new OrderListDataConverter().setJsonData(response)
                            .convert();
                    final OrderListAdapter adapter = new OrderListAdapter(data);
                    mRecyclerView.setAdapter(adapter);

                }).build().get();
    }

    @Override
    public void enqueueAction(Runnable runnable) {

    }
}
