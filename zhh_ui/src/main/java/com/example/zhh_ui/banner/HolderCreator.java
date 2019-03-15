package com.example.zhh_ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * @author brett-zhu
 * created at 2019/3/9 19:34
 */
public class HolderCreator implements CBViewHolderCreator<ImageHolder> {
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
