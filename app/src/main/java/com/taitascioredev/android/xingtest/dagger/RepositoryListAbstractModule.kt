package com.taitascioredev.android.xingtest.dagger

import com.taitascioredev.android.xingtest.data.repository.GithubRepository
import com.taitascioredev.android.xingtest.data.repository.impl.GithubRepositoryImpl
import com.taitascioredev.android.xingtest.domain.usecase.GetXingReposUseCase
import com.taitascioredev.android.xingtest.domain.usecase.impl.GetXingReposUseCaseImpl
import dagger.Binds
import dagger.Module

/**
 * Created by rrtatasciore on 18/01/18.
 */
@Module
abstract class RepositoryListAbstractModule {
    @Binds abstract fun provideGetXingReposUseCase(useCase: GetXingReposUseCaseImpl): GetXingReposUseCase
    @Binds abstract fun provideGithubRepository(repository: GithubRepositoryImpl): GithubRepository
}