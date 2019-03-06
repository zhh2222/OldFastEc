package com.example.brett_zhu.festec.generators;

import com.example.zhh_annotations.annotations.AppRegisterGenerator;
import com.example.zhh_core.wechat.template.AppRegisterTemplate;

/**
 * @author brett-zhu
 * created at 2019/3/6 20:53
 */
@AppRegisterGenerator(
        packageName = "com.example.brett_zhu.festec",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
