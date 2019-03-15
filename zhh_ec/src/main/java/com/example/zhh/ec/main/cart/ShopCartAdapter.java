package com.example.zhh.ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhh.ec.R;
import com.example.zhh_core.app.Zhh;
import com.example.zhh_core.net.RestClient;
import com.example.zhh_ui.recycler.MultipleFields;
import com.example.zhh_ui.recycler.MultipleItemEntity;
import com.example.zhh_ui.recycler.MultipleRecyclerAdapter;
import com.example.zhh_ui.recycler.MultipleViewHolder;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * @author brett-zhu
 * created at 2019/3/13 22:22
 */
public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSelectedAll = false;
    private ICartItemListener mCartItemListener = null;
    private double mTotalPrice = 0.00;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        //添加购物车item布局
        super(data);
        //初始化总价
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(ShopCartItemFields.PRICE);
            final int count = entity.getField(ShopCartItemFields.COUNT);
            final double total = price * count;
            mTotalPrice += total;
        }
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    public void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    public void setCartItemListener(ICartItemListener cartItemListener) {
        mCartItemListener = cartItemListener;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        super.convert(holder, item);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                //先取出所有值
                final int id = item.getField(MultipleFields.ID);
                final String thumb = item.getField(MultipleFields.IMAGE_URL);
                final String title = item.getField(ShopCartItemFields.TITLE);
                final String desc = item.getField(ShopCartItemFields.DESC);
                final int count = item.getField(ShopCartItemFields.COUNT);
                final double price = item.getField(ShopCartItemFields.PRICE);

                //取出所有控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);
                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext).load(thumb).apply(OPTIONS).into(imgThumb);

                //在左侧item渲染之前改变全选与否状态
                item.setField(ShopCartItemFields.IS_SELECTED, mIsSelectedAll);
                final boolean isSelected = item.getField(ShopCartItemFields.IS_SELECTED);
                //根据数据状态显示左侧选中
                if (isSelected) {
                    iconIsSelected.setTextColor(ContextCompat.getColor
                            (Zhh.getApplicationContext(), R.color.app_main));
                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                //添加左侧点击事件
                iconIsSelected.setOnClickListener(v -> {
                    final boolean currentSelected = item.getField(ShopCartItemFields.IS_SELECTED);
                    if (currentSelected) {
                        iconIsSelected.setTextColor(Color.GRAY);
                        item.setField(ShopCartItemFields.IS_SELECTED, false);
                    } else {
                        iconIsSelected.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                        item.setField(ShopCartItemFields.IS_SELECTED, true);
                    }
                });
                //添加加减事件
                iconMinus.setOnClickListener(v -> {
                    final int currentCount = item.getField(ShopCartItemFields.COUNT);
                    if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                        RestClient.builder()
                                .url("shop_cart_data.json")
                                .loader(mContext)
                                .params("count", currentCount)
                                .success(response -> {
                                    int countNum = Integer.parseInt(tvCount.getText().toString());
                                    countNum--;
                                    tvCount.setText(String.valueOf(countNum));
                                    if (mCartItemListener != null) {
                                        mTotalPrice -= price;
                                        final double itemTotal = countNum * price;
                                        mCartItemListener.onItemClick(itemTotal);
                                    }
                                }).build().post();
                    }
                });
                iconPlus.setOnClickListener(v -> {
                    final int currentCount = item.getField(ShopCartItemFields.COUNT);
                    if (Integer.parseInt(tvCount.getText().toString()) >= 1) {
                        RestClient.builder()
                                .url("shop_cart_data.json")
                                .loader(mContext)
                                .params("count", currentCount)
                                .success(response -> {
                                    int countNum = Integer.parseInt(tvCount.getText().toString());
                                    countNum++;
                                    tvCount.setText(String.valueOf(countNum));
                                    if (mCartItemListener != null) {
                                        mTotalPrice += price;
                                        final double itemTotal = countNum * price;
                                        mCartItemListener.onItemClick(itemTotal);
                                    }
                                }).build().post();
                    }
                });
                break;
            default:
                break;
        }
    }
}
