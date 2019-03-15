package com.example.zhh_core.delegates.web.event;

import com.example.zhh_core.net.util.ZhhLogger;

/**
 * @author brett-zhu
 * created at 2019/3/12 21:30
 */
public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        ZhhLogger.e("UndefineEvent",params);
        return null;
    }
}
