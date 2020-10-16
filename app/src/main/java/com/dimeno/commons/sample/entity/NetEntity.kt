package com.dimeno.commons.sample.entity

/**
 * NetEntity
 * Created by wangzhen on 2020/10/14.
 */
class NetEntity {
    var code: Int = 0
    var message: String? = null
    var success: Boolean = false
    var data: DataEntity? = null

    class DataEntity {
        var token: String? = null
    }
}