package com.example.zhh.ec.pay;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zhh.ec.R;
import com.example.zhh_core.app.ConfigKeys;
import com.example.zhh_core.app.Zhh;
import com.example.zhh_core.delegates.ZhhDelegate;
import com.example.zhh_core.net.RestClient;
import com.example.zhh_core.net.callback.ISuccess;
import com.example.zhh_core.util.ZhhLogger;
import com.example.zhh_core.ui.loader.ZhhLoader;
import com.example.zhh_core.wechat.ZhhWeChat;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

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

    public FastPay setPayResultListener(IAlPayResultListener listener) {
        this.mIAlPayResultListener = listener;
        return this;
    }

    public FastPay setOrderId(int orderId) {
        this.mOrderId = orderId;
        return this;
    }

    private void aliPay(int orderId) {
        final String signUrl = "" + orderId;//服务端支付地址+orderId
        //获取签名字符串
        RestClient.builder()
                .url(signUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String paySign = JSON.parseObject(response).getString("result");
                        ZhhLogger.d("PAY_SIGN", paySign);
                        //必须是异步的调用客户端支付接口
                        final PayAsyncTask asyncTask = new PayAsyncTask(mActivity, mIAlPayResultListener);
                        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign);
                    }
                }).build().post();
    }

    private void weChatPay(int orderId) {
        ZhhLoader.stopLoading();
        final String weChatPrePayUrl = "" + orderId;//服务端微信预支付地址+orderId
        ZhhLogger.d("WX_PAY", weChatPrePayUrl);
        final IWXAPI iwxApi = ZhhWeChat.getInstance().getWXAPI();
        final String appId = Zhh.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
        iwxApi.registerApp(appId);
        RestClient.builder()
                .url(weChatPrePayUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject result = JSON.parseObject(response).getJSONObject("result");
                        final String prepayId = result.getString("prepayid");
                        final String partnerId = result.getString("partnerid");
                        final String packageValue = result.getString("package");
                        final String timestamp = result.getString("timestamp");
                        final String nonceStr = result.getString("noncestr");
                        final String paySign = result.getString("sign");
                        final PayReq payReq = new PayReq();
                        payReq.appId = appId;
                        payReq.prepayId = prepayId;
                        payReq.partnerId = partnerId;
                        payReq.packageValue = packageValue;
                        payReq.timeStamp = timestamp;
                        payReq.nonceStr = nonceStr;
                        payReq.sign = paySign;

                        iwxApi.sendReq(payReq);
                    }
                }).build().post();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dialog_pay_alipay) {
            aliPay(mOrderId);
            mAlertDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_wechat) {
            weChatPay(mOrderId);
            mAlertDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_cancel) {
            mAlertDialog.cancel();
        }
    }
}
