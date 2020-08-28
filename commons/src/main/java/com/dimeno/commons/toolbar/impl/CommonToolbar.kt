package com.dimeno.commons.toolbar.impl

import android.app.Activity
import android.view.View
import android.widget.TextView
import com.dimeno.commons.R

/**
 * common toolbar
 */
class CommonToolbar(activity: Activity, private val title: String) : Toolbar(activity) {
    override fun layoutRes(): Int {
        return R.layout.toolbar_common_layout
    }

    override fun onViewCreated(view: View) {
        view.apply {
            findViewById<View>(R.id.back).setOnClickListener {
                activity.finish()
            }
            findViewById<TextView>(R.id.title).text = title
        }
    }

}