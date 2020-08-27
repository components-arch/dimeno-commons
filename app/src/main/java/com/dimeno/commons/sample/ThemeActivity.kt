package com.dimeno.commons.sample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.dimeno.commons.toolbar.factory.ToolbarFactory

/**
 * ThemeActivity
 * Created by wangzhen on 2020/8/27.
 */
class ThemeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)
    }

    override fun configToolbar(toolbar: Toolbar) {
        ToolbarFactory.createToolbar(this, toolbar, "哈哈", R.mipmap.ic_more).apply {
            findViewById<View>(R.id.menu).setOnClickListener {
                Toast.makeText(context, "click menu", Toast.LENGTH_SHORT).show()
            }
        }
    }
}