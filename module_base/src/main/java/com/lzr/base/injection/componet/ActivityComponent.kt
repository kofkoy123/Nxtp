package com.lzr.base.injection.componet

import android.app.Activity
import android.content.Context
import com.lzr.base.injection.ActivityScope
import com.lzr.base.injection.module.ActivityModule
import com.lzr.base.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle3.LifecycleProvider
import dagger.Component

/*
    Activity级别Component
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class,
    LifecycleProviderModule::class))
interface ActivityComponent {

    fun activity():Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}
