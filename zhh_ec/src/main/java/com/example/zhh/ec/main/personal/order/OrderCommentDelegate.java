package com.example.zhh.ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.zhh.ec.R;
import com.example.zhh.ec.R2;
import com.example.zhh_core.delegates.ZhhDelegate;
import com.example.zhh_core.util.callbacks.CallbackManager;
import com.example.zhh_core.util.callbacks.CallbackType;
import com.example.zhh_core.util.callbacks.IGlobalCallback;
import com.example.zhh_ui.widget.AutoPhotoLayout;
import com.example.zhh_ui.widget.StarLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author brett-zhu
 * created at 2019/3/18 11:43
 */
public class OrderCommentDelegate extends ZhhDelegate {

    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout = null;

    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout = null;

    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit() {
        Toast.makeText(getContext(), "评分:" + mStarLayout.getStarCount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(Uri args) {
                        mAutoPhotoLayout.onCropTarget(args);
                    }
                });
    }

    @Override
    public void enqueueAction(Runnable runnable) {

    }
}
