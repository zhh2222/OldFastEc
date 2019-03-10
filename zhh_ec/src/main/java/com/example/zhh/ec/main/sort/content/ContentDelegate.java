package com.example.zhh.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.zhh.ec.R;
import com.example.zhh.ec.R2;
import com.example.zhh_core.delegates.ZhhDelegate;
import com.example.zhh_core.net.RestClient;

import java.util.List;

import butterknife.BindView;

/**
 * @author brett-zhu
 * created at 2019/3/10 15:55
 */
public class ContentDelegate extends ZhhDelegate {

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;
    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    private void initData() {
        RestClient.builder().url("sort_content_data_1.json?contentId=" + mContentId)
                .success(response -> {
                    mData = new SectionDataConverter().convert(response);
                    final SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content,
                            R.layout.item_section_header, mData);
                    mRecyclerView.setAdapter(sectionAdapter);

                }).build().get();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }
}
