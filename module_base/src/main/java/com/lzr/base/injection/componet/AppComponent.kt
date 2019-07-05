package com.lzr.base.injection.componet

import android.content.Context
import com.lzr.base.injection.module.AppModule

import dagger.Component
import javax.inject.Singleton

/*
    Application级别Component
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun context():Context
}
