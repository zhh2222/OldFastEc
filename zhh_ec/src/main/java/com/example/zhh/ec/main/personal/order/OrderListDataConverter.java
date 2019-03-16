package com.example.zhh.ec.main.personal.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.zhh_ui.recycler.DataConverter;
import com.example.zhh_ui.recycler.MultipleFields;
import com.example.zhh_ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author brett-zhu
 * created at 2019/3/16 13:25
 */
public class OrderListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataList = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int dataListSize = dataList.size();
        for (int i = 0; i < dataListSize; i++) {
            final JSONObject data = dataList.getJSONObject(i);
            int id = data.getInteger("id");
            String thumb = data.getString("thumb");
            String title = data.getString("title");
            double price = data.getDouble("price");
            String time = data.getString("time");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(MultipleFields.TITLE, title)
                    .setField(OrderItemFields.PRICE, price)
                    .setField(OrderItemFields.TIME, time)
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
