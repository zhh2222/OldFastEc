package com.example.brett_zhu.festec.generators;

import com.example.zhh_annotations.annotations.EntryGenerator;
import com.example.zhh_core.wechat.template.WXEntryTemplate;

/**
 * @author brett-zhu
 * created at 2019/3/6 20:52
 */
@EntryGenerator(
        packageName = "com.example.brett_zhu.festec",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
