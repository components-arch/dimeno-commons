package com.dimeno.commons.sample.network

import android.os.Bundle
import android.view.View
import com.dimeno.commons.sample.BaseActivity
import com.dimeno.commons.sample.R
import com.dimeno.commons.sample.entity.NetEntity
import com.dimeno.commons.storage.SPHelper
import com.dimeno.commons.toolbar.impl.CommonToolbar
import com.dimeno.commons.toolbar.impl.Toolbar
import com.dimeno.commons.utils.T
import com.dimeno.network.callback.LoadingCallback

/**
 * NetworkEncryptionActivity
 * Created by wangzhen on 2020/10/14.
 */
class NetworkEncryptionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_encryption)
    }

    override fun createToolbar(): Toolbar? {
        return CommonToolbar(this, "网络加密")
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_login -> {
                login()
            }
            R.id.btn_try -> {
                request()
            }
        }
    }

    private fun request() {
        TryTask(object : LoadingCallback<NetEntity>() {
            override fun onSuccess(data: NetEntity) {
                if (data.success) {
                    T.show("测试成功")
                }
            }
        }).exe()
    }

    private fun login() {
        LoginTask(object : LoadingCallback<NetEntity>() {
            override fun onSuccess(data: NetEntity) {
                if (data.success) {
                    data.data?.let {
                        SPHelper.get().put("token", it.token).commit()
                        T.show("登录成功")
                    }
                } else {
                    T.show(data.message)
                }
            }

            override fun onError(code: Int, message: String) {
                T.show(message)
            }
        }).put("account", "admin").put("password", "111111").exe()
    }
}