package com.dimeno.commons.toolbar.impl

import android.app.Activity
import android.view.View
import android.widget.TextView
import com.dimeno.commons.R

/**
 * toolbar with text menu
 * Created by wangzhen on 2020/8/28.
 */
class TextMenuToolbar(activity: Activity, private val title: String, private val menu: String) : Toolbar(activity) {
    override fun layoutRes(): Int {
        return R.layout.toolbar_text_menu_layout
    }

    override fun onViewCreated(view: View) {
        view.apply {
            findViewById<View>(R.id.back).setOnClickListener {
                activity.finish()
            }
            findViewById<TextView>(R.id.title).text = title
            findViewById<TextView>(R.id.menu).text = menu
        }
    }

}