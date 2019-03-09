package com.example.zhh_core.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zhh_core.net.RestClient;
import com.example.zhh_core.net.util.ZhhLogger;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

/**
 * @author brett-zhu
 * created at 2019/3/8 20:58
 */
public abstract class BaseWXEntryActivity extends BaseWXActivity {
    //用户登录成功后的回调
    protected abstract void onSignInSuccess(String userInfo);

    //微信发送请求到第三方应用后的回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //第三方应用发送请求到微信后的回调
    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp) baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(ZhhWeChat.APP_ID).append("&secret=").append(ZhhWeChat.APP_SECRET)
                .append("&code=").append(code).append("&grant_type=authorization_code");
        ZhhLogger.d("authUrl", authUrl.toString());
        getAuth(authUrl.toString());
    }

    private void getAuth(String authUrl) {
        RestClient.builder().url(authUrl)
                .success(response -> {

                    final JSONObject authObj = JSON.parseObject(response);
                    final String accessToken = authObj.getString("access_token");
                    final String openId = authObj.getString("openid");
                    final StringBuilder userInfoUrl = new StringBuilder();
                    userInfoUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                            .append(accessToken)
                            .append("&openid=")
                            .append(openId)
                            .append("&lang=")
                            .append("zh_CN");
                    ZhhLogger.d("userInfoUrl", userInfoUrl.toString());
                    getUserInfo(userInfoUrl.toString());
                }).build().get();
    }

    private void getUserInfo(String userInfoUrl) {
        RestClient.builder().url(userInfoUrl)
                .success(this::onSignInSuccess).failure(() -> {

        }).error((code, msg) -> {

        }).build().get();
    }
}
