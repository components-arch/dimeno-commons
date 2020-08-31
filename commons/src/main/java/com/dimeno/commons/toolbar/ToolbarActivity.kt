package com.dimeno.commons.toolbar

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.dimeno.commons.R
import com.dimeno.commons.toolbar.impl.Toolbar

/**
 * toolbar activity
 * Created by wangzhen on 2020/8/28.
 */
open class ToolbarActivity : AppCompatActivity() {

    @CallSuper
    override fun onPostCreate(savedInstanceState: Bundle?) {
        (window.decorView as ViewGroup).getChildAt(0).fitsSystemWindows = false
        findViewById<View>(R.id.action_bar_root)?.fitsSystemWindows = false
        super.onPostCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        setContentView(layoutInflater.inflate(layoutResID, null))
    }

    override fun setContentView(view: View) {
        configContentView(view)
    }

    private fun configContentView(view: View) {
        createToolbar()?.let { bar ->
            val container = WindowInsetsFrameLayout(this)
            container.addView(view.apply {
                fitsSystemWindows = true
            }, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT).apply {
                topMargin = resources.getDimension(R.dimen.toolbar_height).toInt()
            })
            bar.createView().apply {
                fitsSystemWindows = true
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                    setPadding(paddingLeft, paddingTop + statusBarHeight(), paddingRight, paddingBottom)
                } else {
                    setTag(R.id.tag_fit_ignore_bottom, true)
                }
                container.addView(this, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT))
            }
            super.setContentView(container)
        } ?: super.setContentView(view)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            supportActionBar?.hide()
        }
    }

    open fun createToolbar(): Toolbar? {
        return null
    }

    /**
     * get status bar height
     *
     * @return status bar height
     */
    private fun statusBarHeight(): Int {
        var result = 0
        resources.getIdentifier("status_bar_height", "dimen", "android").also { resourceId ->
            if (resourceId > 0) {
                result = resources.getDimensionPixelSize(resourceId)
            }
        }
        return result
    }
}