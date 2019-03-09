package com.example.zhh_core.wechat.template;

import com.example.zhh_core.wechat.BaseWXEntryActivity;
import com.example.zhh_core.wechat.ZhhWeChat;

/**
 * @author brett-zhu
 * created at 2019/3/6 20:50
 */
public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        ZhhWeChat.getInstance().getSignInCallBack().onSignInSuccess(userInfo);
    }
}
