package com.dimeno.commons.toolbar.impl

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.dimeno.commons.R

/**
 * toolbar with icon menu
 * Created by wangzhen on 2020/8/28.
 */
class IconMenuToolbar(activity: Activity, private val title: String, private val menu: Int) : Toolbar(activity) {
    override fun layoutRes(): Int {
        return R.layout.toolbar_icon_menu_layout
    }

    override fun onViewCreated(view: View) {
        view.apply {
            findViewById<View>(R.id.back).setOnClickListener {
                activity.finish()
            }
            findViewById<TextView>(R.id.title).text = title
            findViewById<ImageView>(R.id.menu).setImageResource(menu)
        }
    }

}