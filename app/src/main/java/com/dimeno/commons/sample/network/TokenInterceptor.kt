package com.dimeno.commons.sample.network

import com.dimeno.commons.storage.SPHelper
import com.dimeno.commons.utils.L
import okhttp3.Interceptor
import okhttp3.Response

/**
 * token interceptor
 * Created by wangzhen on 2020/10/14.
 */
class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SPHelper.get().get("token", "")
        L.e("-> token : $token")
        val response = chain.proceed(chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build())
        response.headers.forEach { header ->
            L.e("-> header ${header.first} : ${header.second}")
        }
        return response
    }
}