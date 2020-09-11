package com.dimeno.commons.widget.fitwindows

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Insets
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.WindowInsets
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.dimeno.commons.R

/**
 * a frame layout which can dispatch windows insets to all children
 * Created by wangzhen on 2020/9/11.
 */
class FitWindowsFrameLayout : FrameLayout {
    private var mFitType: Int = FitType.BOTH

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, -1)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FitWindowsFrameLayout)
        mFitType = typedArray.getInt(R.styleable.FitWindowsFrameLayout_fitType, FitType.BOTH)
        typedArray.recycle()
    }

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    override fun dispatchApplyWindowInsets(insets: WindowInsets): WindowInsets {
        val childCount = childCount
        for (index in 0 until childCount) {
            getChildAt(index).dispatchApplyWindowInsets(makeInsets(insets))
        }
        return insets
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    override fun addView(child: View) {
        super.addView(child)
        child.requestApplyInsets()
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    private fun makeInsets(insets: WindowInsets): WindowInsets {
        val left = insets.systemWindowInsetLeft
        val top = if (mFitType == FitType.BOTTOM) 0 else insets.systemWindowInsetTop
        val right = insets.systemWindowInsetRight
        val bottom = if (mFitType == FitType.TOP) 0 else insets.systemWindowInsetBottom
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            insets.replaceSystemWindowInsets(left, top, right, bottom)
        } else {
            WindowInsets.Builder(insets)
                    .setSystemWindowInsets(Insets.of(left, top, right, bottom))
                    .build()
        }
    }
}