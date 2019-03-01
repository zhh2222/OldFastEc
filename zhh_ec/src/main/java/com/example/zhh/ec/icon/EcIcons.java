package com.example.zhh.ec.icon;

import com.joanzapata.iconify.Icon;

import java.util.Calendar;

/**
 * @author brett-zhu
 * created at 2019/3/1 20:42
 */
public enum  EcIcons implements Icon {
    icon_ali_pay('\ue606'),
    icon_scan('\ue606');

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
