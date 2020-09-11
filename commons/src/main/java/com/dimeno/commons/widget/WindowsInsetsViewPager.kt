package com.dimeno.commons.widget

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager

/**
 * a ViewPager which can dispatch windows insets to all children
 * Created by wangzhen on 2020/9/11.
 */
class WindowsInsetsViewPager : ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun dispatchApplyWindowInsets(insets: WindowInsets?): WindowInsets {
        var result = super.dispatchApplyWindowInsets(insets)
        if (!result.isConsumed) {
            for (i in 0..childCount) {
                getChildAt(i)?.let {
                    result = it.dispatchApplyWindowInsets(insets)
                }
            }
        }
        return result
    }

    override fun addView(child: View) {
        super.addView(child)
        ViewCompat.requestApplyInsets(child)
    }
}