package com.dimeno.commons.ui;

import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ui util
 * Created by wangzhen on 2020/8/19.
 */
public class AppUtils {

    private static Handler sHandler;

    private AppUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static Application mApplication;

    public static void install(Application application) {
        mApplication = application;
    }

    public static Application getApplication() {
        return mApplication;
    }

    public static Context getContext() {
        return mApplication;
    }

    /**
     * dip to px
     */
    public static int dip2px(float dip) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getContext().getResources().getDisplayMetrics()));
    }

    /**
     * px to dip
     */
    public static float px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return px / scale;
    }

    /**
     * sp to px
     */
    public static int sp2px(float spVal) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, getContext().getResources().getDisplayMetrics()));
    }

    /**
     * px to sp
     */
    public static float px2sp(float pxVal) {
        return (pxVal / getContext().getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * get screen width in pixels
     */
    public static int getScreenWidthPixels() {
        WindowManager windowManager = (WindowManager)
                getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getRealSize(point);
        return Math.min(point.x, point.y);
    }

    /**
     * get screen height in pixels
     */
    public static int getScreenHeightPixels() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getRealSize(point);
        return Math.max(point.x, point.y);
    }

    /**
     * get handler on main thread
     */
    public static Handler getHandler() {
        if (sHandler == null) {
            sHandler = new Handler(Looper.getMainLooper());
        }
        return sHandler;
    }

    /**
     * get resources
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * get color
     */
    public static int getColor(int resId) {
        return ContextCompat.getColor(getContext(), resId);
    }

    /**
     * get drawable
     */
    public static Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(getContext(), resId);
    }

    /**
     * get string
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * get string array
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * get dimension in pixels
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * whether current thread is main thread or not
     */
    public static boolean isOnMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    /**
     * show soft keyboard(force)
     * notes:
     * the soft keyboard can not hide automatically
     *
     * @param view view
     */
    public static void showSoftInput(View view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * show soft keyboard(force)
     *
     * @return true / false
     */
    public static boolean hideSoftInput(View view) {
        view.clearFocus();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * set views status(BFS)
     *
     * @param view    Assign view
     * @param enabled {@link View#setEnabled(boolean)}
     */
    public static void setViewEnabled(View view, boolean enabled) {
        if (view != null) {
            Queue<View> queue = new LinkedList<>();
            queue.add(view);
            while (!queue.isEmpty()) {
                View poll = queue.poll();
                if (poll != null) {
                    poll.setEnabled(enabled);
                    if (poll instanceof ViewGroup) {
                        ViewGroup parent = (ViewGroup) poll;
                        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
                            queue.add(parent.getChildAt(i));
                        }
                    }
                }
            }
        }
    }

    /**
     * get status bar height
     *
     * @return status bar height
     */
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * get app name
     */
    public static String getAppName() {
        PackageManager pm;
        String appName = "";
        if (getContext() != null) {
            pm = getContext().getPackageManager();
            appName = getContext().getApplicationInfo().loadLabel(pm).toString();
        }

        return appName;
    }

    /**
     * get version name
     *
     * @return version name
     */
    public static String getVersionName() {
        try {
            return getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1.0.0";
    }

    /**
     * get version code
     *
     * @return version code
     */
    public static int getVersionCode() {
        try {
            return getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * resolve the real activity from context
     *
     * @param ctx context
     * @return activity
     */
    @Nullable
    public static Activity getRealActivity(Context ctx) {
        while (ctx instanceof ContextWrapper) {
            if (ctx instanceof Activity) {
                return (Activity) ctx;
            }
            ctx = ((ContextWrapper) ctx).getBaseContext();
        }
        return null;
    }

    /**
     * get real file path from Uri
     *
     * @param uri uri
     * @return path
     */
    @Nullable
    public static String getRealFilePath(Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = getContext().getContentResolver().query(uri, new String[]{MediaStore
                    .Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
