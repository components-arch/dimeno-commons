package com.dimeno.commons.sample.network

import com.dimeno.commons.storage.SPHelper
import com.dimeno.commons.utils.AppUtils
import com.dimeno.commons.utils.L
import com.dimeno.commons.utils.T
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.closeQuietly

/**
 * request interceptor
 * Created by wangzhen on 2020/10/14.
 */
class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        L.e("-> url : ${request.url}")

        val builder = request.newBuilder().header("x-requested-with", "XMLHttpRequest")
        if (!request.url.toString().contains("/idm/login")) {
            val cookie = SPHelper.get().get("cookie", "")
            L.e("-> cookie request : $cookie")
            builder.header("Cookie", "${CookieManager.key}=$cookie")
        }

        val response = chain.proceed(builder.build())

        response.headers["sessionstatus"]?.let { status ->
            if (status == "timeout") {
                L.e("-> cookie expires")
                response.closeQuietly()
                AppUtils.getHandler().post {
                    SPHelper.get().put("cookie", "").commit()
                    T.show("Session过期，请重新登录")
                }
            }
        }
        return response
    }
}