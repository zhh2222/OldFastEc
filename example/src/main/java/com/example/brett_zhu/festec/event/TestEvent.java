package com.example.brett_zhu.festec.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.example.zhh_core.delegates.web.event.Event;

/**
 * @author brett-zhu
 * created at 2019/3/12 21:35
 */
public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            getWebView().post(() -> {
                webView.evaluateJavascript("nativeCall();", null);
            });
        }
        return null;
    }
}
