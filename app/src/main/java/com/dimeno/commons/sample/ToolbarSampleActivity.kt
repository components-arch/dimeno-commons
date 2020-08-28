package com.dimeno.commons.sample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dimeno.commons.toolbar.impl.IconMenuToolbar
import com.dimeno.commons.toolbar.impl.Toolbar
import com.wangzhen.statusbar.DarkStatusBar

/**
 * ToolbarSampleActivity
 * Created by wangzhen on 2020/8/28.
 */
class ToolbarSampleActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DarkStatusBar.get().fitDark(this)
        setContentView(R.layout.activity_topbar_sample)
    }

    override fun createTopBar(): Toolbar? {
        val toolbar = IconMenuToolbar(this, "toolbar", R.mipmap.ic_more)
        toolbar.createView().findViewById<View>(R.id.menu).setOnClickListener {
            Toast.makeText(this, "menu", Toast.LENGTH_SHORT).show()
        }
        return toolbar
    }
}