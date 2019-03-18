package com.example.zhh.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.zhh.ec.R;
import com.example.zhh.ec.R2;
import com.example.zhh.ec.main.personal.PersonalClickListener;
import com.example.zhh.ec.main.personal.address.AddressDelegate;
import com.example.zhh.ec.main.personal.list.ListAdapter;
import com.example.zhh.ec.main.personal.list.ListBean;
import com.example.zhh.ec.main.personal.list.ListItemType;
import com.example.zhh_core.delegates.ZhhDelegate;
import com.example.zhh_core.util.callbacks.CallbackManager;
import com.example.zhh_core.util.callbacks.CallbackType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author brett-zhu
 * created at 2019/3/18 10:31
 */
public class SettingsDelegate extends ZhhDelegate {

    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean push = new ListBean.Builder().setItemType(ListItemType.ITEM_SWITCH)
                .setId(1)
                .setText("消息推送")
                .setDelegate(new AddressDelegate())
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH).executeCallback(null);
                            Toast.makeText(getContext(),"打开推送",Toast.LENGTH_SHORT).show();
                        } else {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_STOP_PUSH).executeCallback(null);
                            Toast.makeText(getContext(),"关闭推送",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .build();
        final ListBean about = new ListBean.Builder().setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new AboutDelegate())
                .setText("关于")
                .build();
        final List<ListBean> data = new ArrayList<>();
        data.add(push);
        data.add(about);

        //设置RecyclerView
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter listAdapter = new ListAdapter(data);
        mRecyclerView.setAdapter(listAdapter);
        mRecyclerView.addOnItemTouchListener(new SettingsClickListener(this));
    }

    @Override
    public void enqueueAction(Runnable runnable) {

    }
}
