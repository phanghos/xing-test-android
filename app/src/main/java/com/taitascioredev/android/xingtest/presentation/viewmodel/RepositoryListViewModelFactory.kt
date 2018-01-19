package com.taitascioredev.android.xingtest.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.taitascioredev.android.xingtest.domain.usecase.GetXingReposUseCase
import javax.inject.Inject

/**
 * Created by rrtatasciore on 18/01/18.
 */
class RepositoryListViewModelFactory @Inject constructor(private val useCase: GetXingReposUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepositoryListViewModel(useCase) as T
    }
}