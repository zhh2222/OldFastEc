package com.example.zhh.ec.pay;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.zhh.ec.R;
import com.example.zhh_core.delegates.ZhhDelegate;
import com.example.zhh_core.net.RestClient;

import java.util.Objects;

/**
 * @author brett-zhu
 * created at 2019/3/15 21:59
 */
public class FastPay implements View.OnClickListener {
    //设置支付回调监听
    private IAlPayResultListener mIAlPayResultListener = null;
    private Activity mActivity = null;
    private AlertDialog mAlertDialog = null;
    private int mOrderId = -1;

    private FastPay(ZhhDelegate delegate) {
        this.mActivity = delegate.getProxyActivity();
        this.mAlertDialog = new AlertDialog.Builder(Objects.requireNonNull(delegate.getContext())).create();
    }

    public static FastPay create(ZhhDelegate delegate) {
        return new FastPay(delegate);
    }

    public void beginPayDialog() {
        mAlertDialog.show();
        final Window window = mAlertDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);
            window.findViewById(R.id.btn_dialog_pay_alipay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);
        }
    }

    public final void aliPay(int orderId) {
        final String signUrl = "";
        //获取签名字符串
        RestClient.builder()
                .url(signUrl)
                .success(response -> {

                }).build().post();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dialog_pay_alipay) {
            mAlertDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_wechat) {
            mAlertDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_cancel) {
            mAlertDialog.cancel();
        }
    }
}
