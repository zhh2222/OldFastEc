package com.example.brett_zhu.festec.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zhh_core.delegates.web.event.Event;
import com.example.zhh_core.util.ZhhLogger;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * @author brett-zhu
 * created at 2019/3/19 11:16
 */
public class ShareEvent extends Event {
    @Override
    public String execute(String params) {

        ZhhLogger.d("ShareEvent",params);
        final JSONObject object = JSON.parseObject(params).getJSONObject("params");
        final String title = object.getString("title");
        final String url = object.getString("url");
        final String imageUrl = object.getString("imageUrl");
        final String text = object.getString("text");

        final OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setText(text);
        oks.setImageUrl(imageUrl);
        oks.setUrl(url);
        oks.show(getContext());

        return null;
    }
}
