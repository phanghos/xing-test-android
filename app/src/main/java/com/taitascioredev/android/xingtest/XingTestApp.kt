package com.taitascioredev.android.xingtest

import com.taitascioredev.android.xingtest.dagger.AppModule
import com.taitascioredev.android.xingtest.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by rrtatasciore on 18/01/18.
 */
class XingTestApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}