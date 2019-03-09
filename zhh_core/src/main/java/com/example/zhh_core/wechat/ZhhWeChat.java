package com.example.zhh_core.wechat;

import android.app.Activity;

import com.example.zhh_core.app.ConfigKeys;
import com.example.zhh_core.app.Zhh;
import com.example.zhh_core.wechat.callbacks.IWeChatSignInCallBack;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author brett-zhu
 * created at 2019/3/6 23:23
 */
public class ZhhWeChat {
    public static final String APP_ID = Zhh.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Zhh.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallBack mSignInCallBack = null;

    private static final class Holder {
        private static final ZhhWeChat INSTANCE = new ZhhWeChat();
    }

    public static ZhhWeChat getInstance() {
        return Holder.INSTANCE;
    }

    private ZhhWeChat() {
        final Activity activity = Zhh.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }

    public ZhhWeChat onSignSuccess(IWeChatSignInCallBack signInCallBack) {
        mSignInCallBack = signInCallBack;
        return this;
    }

    public IWeChatSignInCallBack getSignInCallBack() {
        return mSignInCallBack;
    }
}
