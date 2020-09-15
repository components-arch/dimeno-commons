package com.dimeno.commons.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.dimeno.commons.annotation.DoubleClick
import com.dimeno.commons.sample.windowinsets.ViewPagerSampleActivity
import com.dimeno.commons.sample.windowinsets.WindowInsetsActivity
import com.dimeno.commons.utils.L
import com.dimeno.commons.utils.T

/**
 * MainActivity
 * Created by wangzhen on 2020/8/19.
 */
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @DoubleClick
    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_double_click -> {
                T.show("-> click")
            }
            R.id.btn_toolbar ->
                startActivity(Intent(this, ToolbarSampleActivity::class.java))
            R.id.btn_toolbar_scroll ->
                startActivity(Intent(this, ToolbarScrollActivity::class.java))
            R.id.btn_view_pager ->
                startActivity(Intent(this, ViewPagerSampleActivity::class.java))
            R.id.btn_window_insets -> {
                startActivity(Intent(this, WindowInsetsActivity::class.java))
                L.e("打开WindowInsets页面")
            }
        }
    }
}