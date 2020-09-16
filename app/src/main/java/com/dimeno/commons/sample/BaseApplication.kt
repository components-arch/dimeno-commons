package com.dimeno.commons.sample

import android.app.Application
import com.dimeno.commons.utils.AppUtils

/**
 * BaseApplication
 * Created by wangzhen on 2020/9/15.
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppUtils.install(this, true)
    }
}