package com.dimeno.commons.toolbar.factory

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.dimeno.commons.R

class ToolbarFactory {
    companion object {
        /**
         * create a normal toolbar
         * eg: title in center
         */
        fun createToolbar(activity: Activity, toolbar: Toolbar, title: String): View {
            return View.inflate(activity, R.layout.toolbar_common_layout, toolbar).apply {
                findViewById<TextView>(R.id.title).text = title
            }
        }

        /**
         * create a toolbar with center title and right text menu
         */
        fun createToolbar(activity: Activity, toolbar: Toolbar, title: String, menu: String): View {
            return View.inflate(activity, R.layout.toolbar_text_menu_layout, toolbar).apply {
                findViewById<TextView>(R.id.title).text = title
                findViewById<TextView>(R.id.menu).text = menu
            }
        }

        /**
         * create a toolbar with center title and right icon menu
         */
        fun createToolbar(activity: Activity, toolbar: Toolbar, title: String, icon: Int): View {
            return View.inflate(activity, R.layout.toolbar_icon_menu_layout, toolbar).apply {
                findViewById<TextView>(R.id.title).text = title
                findViewById<ImageView>(R.id.menu).setImageResource(icon)
            }
        }
    }

}