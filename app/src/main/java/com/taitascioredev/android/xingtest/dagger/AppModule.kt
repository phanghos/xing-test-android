package com.taitascioredev.android.xingtest.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rrtatasciore on 18/01/18.
 */
@Module
class AppModule(private val application: Application) {
    @Provides @Singleton fun provideContext(): Context = application.applicationContext
}