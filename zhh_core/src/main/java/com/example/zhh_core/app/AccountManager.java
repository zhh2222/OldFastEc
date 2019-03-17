package com.example.zhh_core.app;

import com.example.zhh_core.util.ZhhPreference;

/**
 * @author brett-zhu
 * created at 2019/3/5 21:40
 */
public class AccountManager {
    private enum SignTag {
        SIGN_TAG
    }

    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state) {
        ZhhPreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return ZhhPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }


}
