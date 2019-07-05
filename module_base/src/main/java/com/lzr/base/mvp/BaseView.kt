package com.lzr.base.mvp/*
    MVP中视图回调 基类
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(text:String)
    fun onDataIsNull(){}//默认实现
}
