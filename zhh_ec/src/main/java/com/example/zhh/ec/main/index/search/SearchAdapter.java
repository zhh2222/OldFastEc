package com.example.zhh.ec.main.index.search;

import android.support.v7.widget.AppCompatTextView;

import com.example.zhh.ec.R;
import com.example.zhh_ui.recycler.MultipleFields;
import com.example.zhh_ui.recycler.MultipleItemEntity;
import com.example.zhh_ui.recycler.MultipleRecyclerAdapter;
import com.example.zhh_ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * @author brett-zhu
 * created at 2019/3/19 13:18
 */
public class SearchAdapter extends MultipleRecyclerAdapter {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        super.convert(holder, item);
        switch (item.getItemType()) {
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = item.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;
            default:
                break;
        }
    }
}
