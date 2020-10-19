package com.dimeno.commons.sample.network.task

import com.dimeno.network.callback.LoadingCallback
import com.dimeno.network.task.PostFormTask

/**
 * LoginTask
 * Created by wangzhen on 2020/10/14.
 */
class LoginTask(callback: LoadingCallback<*>?) : PostFormTask(callback) {
    override fun getApi(): String {
        return "/idm/login"
    }
}