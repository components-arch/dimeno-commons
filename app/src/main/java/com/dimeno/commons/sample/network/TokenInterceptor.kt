package com.dimeno.commons.sample.network

import com.dimeno.commons.storage.SPHelper
import com.dimeno.commons.utils.AppUtils
import com.dimeno.commons.utils.L
import com.dimeno.commons.utils.T
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.closeQuietly

/**
 * token interceptor
 * Created by wangzhen on 2020/10/14.
 */
class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        L.e("-> url : ${request.url}")

        val token = SPHelper.get().get("token", "")
        L.e("-> token : $token")

        val response = chain.proceed(request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build())

        response.headers.forEach { header ->
            L.e("-> header [ ${header.first} : ${header.second} ]")
        }

        response.headers["code"]?.let { code ->
            if (code == "700") {
                response.closeQuietly()
                AppUtils.getHandler().post {
                    T.show("Token过期，请重新登录")
                }
            }
        }
        return response
    }
}