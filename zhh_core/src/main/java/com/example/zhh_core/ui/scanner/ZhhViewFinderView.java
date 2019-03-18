package com.example.zhh_core.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * @author brett-zhu
 * created at 2019/3/18 22:41
 */
public class ZhhViewFinderView extends ViewFinderView {
    public ZhhViewFinderView(Context context) {
        this(context, null);
    }

    public ZhhViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mSquareViewFinder = true;
        mBorderPaint.setColor(Color.YELLOW);
        mLaserPaint.setColor(Color.YELLOW);
    }
}
