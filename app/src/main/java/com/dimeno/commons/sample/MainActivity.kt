package com.dimeno.commons.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dimeno.commons.annotation.DoubleClick
import com.dimeno.commons.sample.windowinsets.WindowInsetSampleActivity

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
            R.id.btn_double_click ->
                Toast.makeText(this, "-> click", Toast.LENGTH_SHORT).show()
            R.id.btn_toolbar ->
                startActivity(Intent(this, ToolbarSampleActivity::class.java))
            R.id.btn_window_inset ->
                startActivity(Intent(this, WindowInsetSampleActivity::class.java))
        }
    }
}