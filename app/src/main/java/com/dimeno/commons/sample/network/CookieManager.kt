package com.dimeno.commons.sample.network

import com.dimeno.commons.storage.SPHelper
import com.dimeno.commons.utils.L
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.util.*

/**
 * cookie manager
 * Created by wangzhen on 2020/10/19.
 */
class CookieManager : CookieJar {
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return Collections.emptyList()
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookies.forEach { cookie ->
            if (key == cookie.name) {
                SPHelper.get().put("cookie", cookie.value).commit()
                L.e("-> cookie saveFromResponse ${cookie.value}")
            }
        }
    }

    companion object {
        const val key = "dimeno.build.shiroCookie"
    }
}