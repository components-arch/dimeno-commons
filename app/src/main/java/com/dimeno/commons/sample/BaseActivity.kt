package com.dimeno.commons.sample

import android.os.Bundle
import com.dimeno.commons.toolbar.ToolbarActivity
import com.wangzhen.statusbar.DarkStatusBar


open class BaseActivity : ToolbarActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DarkStatusBar.get().fitDark(this)
    }
}