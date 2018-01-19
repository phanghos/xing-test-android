package com.taitascioredev.android.xingtest.domain.usecase

import com.taitascioredev.android.xingtest.presentation.model.RepositoryListViewState
import io.reactivex.Observable

/**
 * Created by rrtatasciore on 18/01/18.
 */
interface GetXingReposUseCase {
    fun getXingRepos(): Observable<RepositoryListViewState>
}