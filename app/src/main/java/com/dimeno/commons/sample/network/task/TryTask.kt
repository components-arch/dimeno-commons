package com.dimeno.commons.sample.network.task

import com.dimeno.network.callback.LoadingCallback
import com.dimeno.network.task.GetTask

/**
 * TryTask
 * Created by wangzhen on 2020/10/14.
 */
class TryTask(callback: LoadingCallback<*>?) : GetTask(callback) {
    override fun getApi(): String {
        return "/appApi/tryApi"
    }
}