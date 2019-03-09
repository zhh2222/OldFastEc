package com.example.zhh_core.delegates.bottom;

/**
 * @author brett-zhu
 * created at 2019/3/8 23:04
 */
public final class BottomTabBean {
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getTitle() {
        return TITLE;
    }

    public CharSequence getIcon() {
        return ICON;
    }
}
