package com.example.zhh_core.delegates;

/**
 * @author brett-zhu
 * created at 2019/3/2 12:17
 */
public abstract class ZhhDelegate extends PermissionCheckerDelegate {
    @SuppressWarnings("unchecked")
    public <T extends ZhhDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
