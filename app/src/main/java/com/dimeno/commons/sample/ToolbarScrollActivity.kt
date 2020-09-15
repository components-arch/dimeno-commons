package com.dimeno.commons.sample

import android.os.Bundle
import com.dimeno.commons.toolbar.impl.CommonToolbar
import com.dimeno.commons.toolbar.impl.Toolbar

/**
 * ToolbarScrollActivity
 * Created by wangzhen on 2020/9/15.
 */
class ToolbarScrollActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar_scroll)
    }

    override fun createToolbar(): Toolbar? {
        return CommonToolbar(this,"Toolbar异常")
    }
}