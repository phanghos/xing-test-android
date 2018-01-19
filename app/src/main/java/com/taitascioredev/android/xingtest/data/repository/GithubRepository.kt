package com.taitascioredev.android.xingtest.data.repository

import com.taitascioredev.android.xingtest.presentation.model.RepositoryListViewState
import io.reactivex.Observable

/**
 * Created by rrtatasciore on 18/01/18.
 */
interface GithubRepository {
    fun getXingRepos(): Observable<RepositoryListViewState>
}