package com.dimeno.commons.toolbar

import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.dimeno.commons.R
import com.dimeno.commons.toolbar.impl.Toolbar

/**
 * TopbarActivity
 * Created by wangzhen on 2020/8/28.
 */
open class ToolbarActivity : AppCompatActivity() {

    override fun setContentView(layoutResID: Int) {
        setContentView(layoutInflater.inflate(layoutResID, null))
    }

    override fun setContentView(view: View) {
        configContentView(view)
    }

    private fun configContentView(view: View) {
        createTopBar()?.let { bar ->
            val container = FrameLayout(this)
            container.addView(view.apply {
                fitsSystemWindows = true
            }, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT).apply {
                topMargin = resources.getDimension(R.dimen.toolbar_height).toInt()
            })
            bar.createView().apply {
                setPadding(paddingLeft, paddingTop + statusBarHeight(), paddingRight, paddingBottom)
                container.addView(this, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT))
            }
            super.setContentView(container)
        } ?: super.setContentView(view)
    }

    open fun createTopBar(): Toolbar? {
        return null
    }

    /**
     * get status bar height
     *
     * @return status bar height
     */
    open fun statusBarHeight(): Int {
        var result = 0
        resources.getIdentifier("status_bar_height", "dimen", "android").also { resourceId ->
            if (resourceId > 0) {
                result = resources.getDimensionPixelSize(resourceId)
            }
        }
        return result
    }
}