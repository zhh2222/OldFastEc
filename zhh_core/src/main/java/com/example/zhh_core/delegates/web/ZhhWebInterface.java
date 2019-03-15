package com.example.zhh_core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.example.zhh_core.delegates.web.event.Event;
import com.example.zhh_core.delegates.web.event.EventManager;

/**
 * @author brett-zhu
 * created at 2019/3/11 22:18
 */
public final class ZhhWebInterface {
    private final WebDelegate DELEGATE;

    private ZhhWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static ZhhWebInterface create(WebDelegate delegate) {
        return new ZhhWebInterface(delegate);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }

}
