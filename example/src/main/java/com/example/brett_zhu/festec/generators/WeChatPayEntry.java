package com.example.brett_zhu.festec.generators;

import com.example.zhh_annotations.annotations.PayEntryGenerator;
import com.example.zhh_core.wechat.template.WXEntryTemplate;

/**
 * @author brett-zhu
 * created at 2019/3/6 20:53
 */
@PayEntryGenerator(
        packageName = "com.example.brett_zhu.festec",
        payEntryTemplate = WXEntryTemplate.class
)
public interface WeChatPayEntry {
}
