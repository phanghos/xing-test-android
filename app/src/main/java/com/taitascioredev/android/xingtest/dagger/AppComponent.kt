package com.taitascioredev.android.xingtest.dagger

import com.taitascioredev.android.xingtest.XingTestApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by rrtatasciore on 18/01/18.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, NetModule::class, DataModule::class, ActivityBindingModule::class))
interface AppComponent : AndroidInjector<XingTestApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<XingTestApp>() {
        abstract fun appModule(appModule: AppModule): Builder
        override fun seedInstance(instance: XingTestApp?) {
            appModule(AppModule(instance!!))
        }
    }
}