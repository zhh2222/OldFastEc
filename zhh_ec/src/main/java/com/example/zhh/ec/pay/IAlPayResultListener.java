package com.example.zhh.ec.pay;

/**
 * @author brett-zhu
 * created at 2019/3/15 22:00
 */
public interface IAlPayResultListener {
    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
