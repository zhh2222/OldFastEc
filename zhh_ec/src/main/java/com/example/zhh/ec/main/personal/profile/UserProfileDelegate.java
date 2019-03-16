package com.example.zhh.ec.main.personal.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zhh.ec.R;
import com.example.zhh.ec.R2;
import com.example.zhh.ec.main.personal.list.ListAdapter;
import com.example.zhh.ec.main.personal.list.ListBean;
import com.example.zhh.ec.main.personal.list.ListItemType;
import com.example.zhh.ec.main.personal.settings.NameDelegate;
import com.example.zhh_core.delegates.ZhhDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author brett-zhu
 * created at 2019/3/16 15:36
 */
public class UserProfileDelegate extends ZhhDelegate {

    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean image = new ListBean.Builder().setItemType(ListItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("http://ku.90sjimg.com/element_origin_min_pic/17/12/24/a47c37e3b0bf28a293d62f701847d644.jpg")
                .build();
        final ListBean name = new ListBean.Builder().setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("姓名")
                .setDelegate(new NameDelegate())
                .setValue("未设置姓名")
                .build();
        final ListBean gender = new ListBean.Builder().setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("未设置性别")
                .build();
        final ListBean birth = new ListBean.Builder().setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setValue("未设置生日")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birth);
        //设置RecyclerView
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter listAdapter = new ListAdapter(data);
        mRecyclerView.setAdapter(listAdapter);
        mRecyclerView.addOnItemTouchListener(new UserProfileClickListener(this));
    }

    @Override
    public void enqueueAction(Runnable runnable) {

    }

}
