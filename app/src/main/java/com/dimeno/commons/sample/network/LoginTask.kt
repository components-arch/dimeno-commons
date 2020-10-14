package com.dimeno.commons.sample.network

import com.dimeno.network.callback.LoadingCallback
import com.dimeno.network.task.GetTask

/**
 * LoginTask
 * Created by wangzhen on 2020/10/14.
 */
class LoginTask(callback: LoadingCallback<*>?) : GetTask(callback) {
    override fun getApi(): String {
        return "/appApi/auth"
    }
}