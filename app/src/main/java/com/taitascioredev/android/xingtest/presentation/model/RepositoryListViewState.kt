package com.taitascioredev.android.xingtest.presentation.model

import com.google.auto.value.AutoValue
import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity

/**
 * Created by rrtatasciore on 18/01/18.
 */
@AutoValue
abstract class RepositoryListViewState {

    abstract fun loading(): Boolean

    abstract fun repos(): List<RepositoryEntity>?

    abstract fun error(): Throwable?

    companion object {
        fun create(loading: Boolean, repos: List<RepositoryEntity>?, error: Throwable?): RepositoryListViewState {
            return AutoValue_RepositoryListViewState(loading, repos, error)
        }
        fun success(repos: List<RepositoryEntity>) = create(false, repos, null)
        fun error(error: Throwable) = create(false, null, error)
        fun inFlight() = create(true, null, null)
    }
}