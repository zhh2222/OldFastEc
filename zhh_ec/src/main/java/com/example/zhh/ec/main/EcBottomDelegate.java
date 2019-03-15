package com.example.zhh.ec.main;

import android.graphics.Color;

import com.example.zhh.ec.main.cart.ShopCartDelegate;
import com.example.zhh.ec.main.discovery.DiscoverDelegate;
import com.example.zhh.ec.main.index.IndexDelegate;
import com.example.zhh.ec.main.sort.SortDelegate;
import com.example.zhh_core.delegates.bottom.BaseBottomDelegate;
import com.example.zhh_core.delegates.bottom.BottomItemDelegate;
import com.example.zhh_core.delegates.bottom.BottomTabBean;
import com.example.zhh_core.delegates.bottom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 * @author brett-zhu
 * created at 2019/3/9 10:13
 */
public class EcBottomDelegate extends BaseBottomDelegate {

    private static final String MAIN_PAGE = "主页";
    private static final String CATEGORY = "分类";
    private static final String DISCOVERY = "发现";
    private static final String SHOPPING_CART = "购物车";
    private static final String MINE = "我的";

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", MAIN_PAGE), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", CATEGORY), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", DISCOVERY), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", SHOPPING_CART), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", MINE), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }

    @Override
    public void enqueueAction(Runnable runnable) {

    }
}
