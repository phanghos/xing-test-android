package com.taitascioredev.android.xingtest.dagger

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.taitascioredev.android.xingtest.data.db.XingTestDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rrtatasciore on 18/01/18.
 */
@Module
class AppModule(private val application: Application) {

    @Provides @Singleton fun provideContext(): Context = application.applicationContext

    @Provides @Singleton fun provideXingTestDb(context: Context): XingTestDb {
        return Room.databaseBuilder(context, XingTestDb::class.java, "xing-test-android")
                .allowMainThreadQueries()
                .build()
    }
}