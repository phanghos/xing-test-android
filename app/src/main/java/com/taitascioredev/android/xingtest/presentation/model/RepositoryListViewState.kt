package com.taitascioredev.android.xingtest.presentation.model

import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity

/**
 * Created by rrtatasciore on 18/01/18.
 */
class RepositoryListViewState(
        val loading: Boolean,
        val repos: List<RepositoryEntity>?,
        val error: Throwable?
) {
    companion object {
        fun success(repos: List<RepositoryEntity>) = RepositoryListViewState(false, repos, null)
        fun error(error: Throwable) = RepositoryListViewState(false, null, error)
        fun inFlight() = RepositoryListViewState(true, null, null)
    }
}