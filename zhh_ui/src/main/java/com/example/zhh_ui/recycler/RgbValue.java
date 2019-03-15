package com.example.zhh_ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * @author brett-zhu
 * created at 2019/3/10 10:49
 */
@AutoValue
public abstract class RgbValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}
