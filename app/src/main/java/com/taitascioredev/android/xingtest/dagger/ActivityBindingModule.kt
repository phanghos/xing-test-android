package com.taitascioredev.android.xingtest.dagger

import com.taitascioredev.android.xingtest.presentation.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rrtatasciore on 18/01/18.
 */
@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [(RepositoryListModule::class)])
    abstract fun mainActivity(): MainActivity
}