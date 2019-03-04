package com.example.zhh_core.net.util.timer;

import java.util.TimerTask;

/**
 * @author brett-zhu
 * created at 2019/3/3 16:45
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener ITimerListener) {
        mITimerListener = ITimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
