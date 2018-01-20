package com.taitascioredev.android.xingtest.dagger

import com.taitascioredev.android.xingtest.data.db.XingTestDb
import com.taitascioredev.android.xingtest.data.net.GithubService
import com.taitascioredev.android.xingtest.data.repository.datasource.RepositoryDataStoreFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rrtatasciore on 20/01/18.
 */
@Module
class DataModule {
    @Provides @Singleton
    fun provideRepositoryDataStoreFactory(service: GithubService, db: XingTestDb): RepositoryDataStoreFactory {
        return RepositoryDataStoreFactory(service, db)
    }
}