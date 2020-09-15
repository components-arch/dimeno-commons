package com.dimeno.commons.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * log utils
 * Created by wangzhen on 2020/9/15.
 */
public class L {
    private static boolean DEBUG = AppUtils.isDebug();
    private static String TAG;

    static {
        String appName = AppUtils.getAppName();
        TAG = TextUtils.isEmpty(appName) ? L.class.getName() : appName;
    }

    public static void i(String msg) {
        if (DEBUG)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (DEBUG)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (DEBUG)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (DEBUG)
            Log.v(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (DEBUG)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (DEBUG)
            Log.v(tag, msg);
    }
}
