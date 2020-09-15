package com.dimeno.commons.utils;

import android.annotation.SuppressLint;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * toast utils
 * Created by wangzhen on 2020/9/15.
 */
public class T {
    private static WeakReference<Toast> mCache;

    public static void show(String message) {
        show(message, -1);
    }

    public static void show(String message, int gravity) {
        if (AppUtils.getContext() != null) {
            getToast(message, gravity).show();
        }
    }

    @SuppressLint("ShowToast")
    private static Toast getToast(String message, int gravity) {
        Toast toast;
        if (mCache == null || mCache.get() == null) {
            toast = Toast.makeText(AppUtils.getContext(), "", Toast.LENGTH_SHORT);
            mCache = new WeakReference<>(toast);
        } else {
            toast = mCache.get();
        }
        toast.setText(message);
        if (gravity > 0)
            toast.setGravity(gravity, 0, 0);
        return toast;
    }
}
