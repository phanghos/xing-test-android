package com.taitascioredev.android.xingtest.domain.usecase.impl

import com.taitascioredev.android.xingtest.data.repository.GithubRepository
import com.taitascioredev.android.xingtest.domain.usecase.GetXingReposUseCase
import com.taitascioredev.android.xingtest.presentation.model.RepositoryListViewState
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rrtatasciore on 18/01/18.
 */
class GetXingReposUseCaseImpl @Inject constructor(private val repository: GithubRepository) : GetXingReposUseCase {
    override fun getXingRepos(): Observable<RepositoryListViewState> {
        return repository.getXingRepos()
    }
}