package com.dimeno.commons.toolbar

import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dimeno.commons.R

/**
 * base toolbar activity
 * Created by wangzhen on 2020/8/27.
 */
open class ToolbarActivity : AppCompatActivity() {

    override fun setContentView(layoutResID: Int) {
        setContentView(layoutInflater.inflate(layoutResID, null))
    }

    override fun setContentView(view: View?) {
        setupContentView(view)
    }

    private fun setupContentView(view: View?) {
        if (showToolbar()) {
            val container = FrameLayout(this).apply {
                setBackgroundResource(R.color.colorPrimary)
                fitsSystemWindows = true
                layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            }
            container.addView(view, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT).apply {
                topMargin = resources.getDimension(R.dimen.toolbar_height).toInt()
            })
            layoutInflater.inflate(R.layout.toolbar_layout, container).findViewById<Toolbar>(R.id.toolbar)?.let { toolbar ->
                configToolbar(toolbar)
                toolbar.showOverflowMenu()
                toolbar.setContentInsetsRelative(0, 0)
                setSupportActionBar(toolbar)
                toolbar.setNavigationOnClickListener { finish() }
            }
            super.setContentView(container)
        } else {
            super.setContentView(view)
        }
    }

    /**
     * whether show toolbar, default true
     */
    open fun showToolbar(): Boolean {
        return true
    }

    /**
     * dispatch toolbar to child for further config
     */
    open fun configToolbar(toolbar: Toolbar) {

    }
}