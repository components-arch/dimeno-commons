package com.dimeno.commons.toolbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Insets;
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
    public WindowInsets dispatchApplyWindowInsets(WindowInsets insets) {
        int childCount = getChildCount();
        for (int index = 0; index < childCount; index++) {
            View child = getChildAt(index);
            Object tag = child.getTag(R.id.tag_fit_ignore_bottom);
            boolean fitIgnoreBottom = tag != null && (boolean) tag;

            int insetLeft = insets.getSystemWindowInsetLeft();
            int insetTop = insets.getSystemWindowInsetTop();
            int insetRight = insets.getSystemWindowInsetRight();
            int insetBottom = fitIgnoreBottom ? 0 : insets.getSystemWindowInsetBottom();

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                child.dispatchApplyWindowInsets(insets.replaceSystemWindowInsets(insetLeft, insetTop, insetRight, insetBottom));
            } else {
                child.dispatchApplyWindowInsets(new WindowInsets.Builder(insets)
                        .setSystemWindowInsets(Insets.of(insetLeft, insetTop, insetRight, insetBottom))
                        .build());
            }
        }
        return insets;
    }
}