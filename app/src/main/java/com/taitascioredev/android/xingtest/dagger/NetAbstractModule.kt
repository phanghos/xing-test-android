package com.taitascioredev.android.xingtest.dagger

import com.taitascioredev.android.xingtest.data.net.GithubService
import com.taitascioredev.android.xingtest.data.net.impl.GithubServiceImpl
import dagger.Binds
import dagger.Module

/**
 * Created by rrtatasciore on 20/01/18.
 */
@Module
abstract class NetAbstractModule {
    @Binds abstract fun provideGithubService(service: GithubServiceImpl): GithubService
}