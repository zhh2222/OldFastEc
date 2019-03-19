package com.example.zhh.ec.detail;

import android.view.View;

import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * @author brett-zhu
 * created at 2019/3/19 17:24
 */
public class ScaleUpAnimator extends BaseViewAnimator {
    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "scaleX", 0.9f, 1f, 1.4f, 1.2f, 1),
                ObjectAnimator.ofFloat(target, "scaleY", 0.9f, 1f, 1.4f, 1.2f, 1));
    }
}
