package com.dimeno.commons.toolbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;

import com.dimeno.commons.R;

/**
 * Window insets frame layout
 * Created by wangzhen on 2020/8/29.
 */
public class WindowInsetsFrameLayout extends FrameLayout {

    public WindowInsetsFrameLayout(Context context) {
        this(context, null);
    }

    public WindowInsetsFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WindowInsetsFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        int childCount = getChildCount();
        for (int index = 0; index < childCount; index++) {
            View child = getChildAt(index);
            Object tag = child.getTag(R.id.tag_fit_ignore_bottom);
            boolean fitIgnoreBottom = tag != null && (boolean) tag;
            child.dispatchApplyWindowInsets(insets.replaceSystemWindowInsets(insets.getSystemWindowInsetLeft(),
                    insets.getSystemWindowInsetTop(),
                    insets.getSystemWindowInsetRight(),
                    fitIgnoreBottom ? 0 : insets.getSystemWindowInsetBottom()));
        }
        return insets;
    }
}