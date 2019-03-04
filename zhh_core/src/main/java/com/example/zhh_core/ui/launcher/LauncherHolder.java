package com.example.zhh_core.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;


/**
 * @author brett-zhu
 * created at 2019/3/3 17:50
 */
public class LauncherHolder implements Holder<Integer> {

    private AppCompatTextView mImageView = null;


    @Override
    public View createView(Context context) {
        mImageView = new AppCompatTextView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
