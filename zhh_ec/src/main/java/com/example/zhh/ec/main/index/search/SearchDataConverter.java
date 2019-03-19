package com.example.zhh.ec.main.index.search;

import com.alibaba.fastjson.JSONArray;
import com.example.zhh_core.util.ZhhPreference;
import com.example.zhh_ui.recycler.DataConverter;
import com.example.zhh_ui.recycler.MultipleFields;
import com.example.zhh_ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author brett-zhu
 * created at 2019/3/19 13:11
 */
public class SearchDataConverter extends DataConverter {

    public static final String TAG_SEARCH_HISTORY = "search_history";
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStr = ZhhPreference.getCustomAppProfile(TAG_SEARCH_HISTORY);
        if (!jsonStr.equals("")) {
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText = array.getString(i);
                MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }
        return ENTITIES;
    }
}
