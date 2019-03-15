package com.example.zhh_core.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.zhh_core.app.ConfigKeys;
import com.example.zhh_core.app.Zhh;
import com.example.zhh_core.delegates.IPageLoadListener;
import com.example.zhh_core.delegates.web.WebDelegate;
import com.example.zhh_core.delegates.web.route.Router;
import com.example.zhh_core.net.util.ZhhLogger;
import com.example.zhh_core.net.util.ZhhPreference;
import com.example.zhh_core.ui.loader.ZhhLoader;

/**
 * @author brett-zhu
 * created at 2019/3/11 22:34
 */
public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Zhh.getHandler();

    public void setPageLoadListener(IPageLoadListener listener) {
        mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        ZhhLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    //获取浏览器Cookie
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        //注意，这里的Cookie和Api请求的Cookie是不一样的，这个在网页中不可见
        final String webHost = Zhh.getConfiguration(ConfigKeys.WEB_HOST);
        if (webHost != null) {
            if (manager.hasCookies()) {
                final String cookieStr = manager.getCookie(webHost);
                if (cookieStr != null && !"".equals(cookieStr)) {
                    ZhhPreference.addCustomAppProfile("cookie", cookieStr);
                }
            }
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        ZhhLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(ZhhLoader::stopLoading, 1000);
    }
}
