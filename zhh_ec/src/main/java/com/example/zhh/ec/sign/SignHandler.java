package com.example.zhh.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zhh.ec.database.DatabaseManager;
import com.example.zhh.ec.database.UserProfile;
import com.example.zhh_core.app.AccountManager;

/**
 * @author brett-zhu
 * created at 2019/3/3 22:36
 */
public class SignHandler {
    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);
        //登录成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }

    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);
        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
