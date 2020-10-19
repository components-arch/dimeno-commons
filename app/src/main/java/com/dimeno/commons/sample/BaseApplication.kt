package com.dimeno.commons.sample

import android.app.Application
import com.dimeno.commons.sample.network.CookieManager
import com.dimeno.commons.sample.network.RequestInterceptor
import com.dimeno.commons.utils.AppUtils
import com.dimeno.network.Network
import com.dimeno.network.config.NetConfig

/**
 * BaseApplication
 * Created by wangzhen on 2020/9/15.
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppUtils.install(this, true)
        Network.init(NetConfig.Builder()
                .baseUrl("http://39.108.54.21:14001")
                .interceptor(RequestInterceptor())
                .cookieJar(CookieManager())
                .build())
    }
}