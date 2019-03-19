package com.example.zhh.ec.main.index.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;
import com.example.zhh.ec.R;
import com.example.zhh.ec.R2;
import com.example.zhh_core.delegates.ZhhDelegate;
import com.example.zhh_core.net.RestClient;
import com.example.zhh_core.net.callback.ISuccess;
import com.example.zhh_core.util.ZhhPreference;
import com.example.zhh_ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author brett-zhu
 * created at 2019/3/19 13:07
 */
public class SearchDelegate extends ZhhDelegate {

    @BindView(R2.id.rv_search)
    RecyclerView mRecyclerView = null;

    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchEdit = null;

    @OnClick(R2.id.tv_top_search)
    void onClickSearch() {
        RestClient.builder()
                .url("address.json?key=")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String searchItemText = mSearchEdit.getText().toString();
                        saveItem(searchItemText);
                        mSearchEdit.setText("");
                        //展示一些东西或弹出一段话
                    }
                }).build().get();
    }

    @OnClick(R2.id.icon_top_search_back)
    void onClickBack() {
        getSupportDelegate().pop();
    }

    @SuppressWarnings("unchecked")
    private void saveItem(String item) {
        if (!StringUtils.isEmpty(item) && !StringUtils.isSpace(item)) {
            List<String> history;
            final String historyStr = ZhhPreference.getCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY);
            if (StringUtils.isEmpty(historyStr)) {
                history = new ArrayList<>();
            } else {
                history = JSON.parseObject(historyStr, ArrayList.class);
            }
            history.add(item);
            final String json = JSON.toJSONString(history);
            ZhhPreference.addCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY, json);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_search;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data = new SearchDataConverter().convert();
        final SearchAdapter adapter = new SearchAdapter(data);
        mRecyclerView.setAdapter(adapter);
        final DividerItemDecoration itemDecoration = new DividerItemDecoration();
        itemDecoration.setDividerLookup(new DividerItemDecoration.DividerLookup() {
            @Override
            public Divider getVerticalDivider(int position) {
                return null;
            }

            @Override
            public Divider getHorizontalDivider(int position) {
                return new Divider.Builder()
                        .size(2).margin(20, 20)
                        .color(Color.GRAY).build();
            }
        });
        mRecyclerView.addItemDecoration(itemDecoration);
    }

}
