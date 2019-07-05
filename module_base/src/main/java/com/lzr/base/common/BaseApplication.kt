package com.lzr.base.common

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.lzr.base.injection.componet.AppComponent
import com.lzr.base.injection.componet.DaggerAppComponent
import com.lzr.base.injection.module.AppModule

/**
 * 作者： 10302
 * 创建时间：2019/7/4
 */
open class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        mContext = this
        initAppInjection()
        initARouter()
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


    //ARouter初始化
    private fun initARouter() {
        ARouter.openLog()    // 打印日志
        ARouter.openDebug()
        ARouter.init(this)
    }

    //相当于单例的getInstance
    companion object {
        lateinit var mContext: Context
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}