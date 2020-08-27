package com.dimeno.commons.sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.dimeno.commons.annotation.DoubleClick

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
                Log.e("TAG", "-> click")
            }
            R.id.btn_toolbar -> {
                startActivity(Intent(this, ThemeActivity::class.java))
            }
        }
    }

    override fun showToolbar(): Boolean {
        return false
    }
}