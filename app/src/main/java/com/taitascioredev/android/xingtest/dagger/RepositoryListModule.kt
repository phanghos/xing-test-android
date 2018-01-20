package com.taitascioredev.android.xingtest.dagger

import com.taitascioredev.android.xingtest.data.repository.GithubRepository
import com.taitascioredev.android.xingtest.data.repository.datasource.RepositoryDataStoreFactory
import com.taitascioredev.android.xingtest.data.repository.impl.GithubRepositoryImpl
import com.taitascioredev.android.xingtest.domain.usecase.GetXingReposUseCase
import com.taitascioredev.android.xingtest.domain.usecase.impl.GetXingReposUseCaseImpl
import com.taitascioredev.android.xingtest.presentation.viewmodel.RepositoryListViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by rrtatasciore on 18/01/18.
 */
@Module(includes = [RepositoryListAbstractModule::class])
class RepositoryListModule {

    @Provides @ActivityScope
    fun provideRepositoryListViewModelFactory(useCase: GetXingReposUseCase): RepositoryListViewModelFactory {
        return RepositoryListViewModelFactory(useCase)
    }

    @Provides @ActivityScope
    fun provideGetXingReposUseCaseImpl(repository: GithubRepository): GetXingReposUseCaseImpl {
        return GetXingReposUseCaseImpl(repository)
    }

    @Provides @ActivityScope
    fun provideGithubRepositoryImpl(factory: RepositoryDataStoreFactory): GithubRepositoryImpl {
        return GithubRepositoryImpl(factory)
    }
}