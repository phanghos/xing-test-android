package com.taitascioredev.android.xingtest.data.repository.impl

import com.taitascioredev.android.xingtest.data.net.GithubService
import com.taitascioredev.android.xingtest.data.repository.GithubRepository
import com.taitascioredev.android.xingtest.presentation.model.RepositoryListViewState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrtatasciore on 18/01/18.
 */
class GithubRepositoryImpl @Inject constructor(private val service: GithubService) : GithubRepository {

    private var curPage = 1

    override fun getXingRepos(): Observable<RepositoryListViewState> {
        return service.getXingRepos(curPage++)
                .map { RepositoryListViewState.success(it) }
                .onErrorReturn { RepositoryListViewState.error(it) }
                .startWith(RepositoryListViewState.inFlight())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}