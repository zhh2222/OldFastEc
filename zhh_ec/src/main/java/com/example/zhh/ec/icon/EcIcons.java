package com.example.zhh.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * @author brett-zhu
 * created at 2019/3/1 20:42
 */
public enum  EcIcons implements Icon {
    icon_ali_pay('\ue61f'),
    icon_scan('\ue674');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return 0;
    }
}
