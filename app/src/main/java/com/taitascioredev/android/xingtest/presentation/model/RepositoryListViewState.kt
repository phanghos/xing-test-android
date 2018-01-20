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

    abstract fun empty(): Boolean

    companion object {
        fun create(loading: Boolean, repos: List<RepositoryEntity>?, error: Throwable?, empty: Boolean): RepositoryListViewState {
            return AutoValue_RepositoryListViewState(loading, repos, error, empty)
        }
        fun success(repos: List<RepositoryEntity>) = create(false, repos, null, false)
        fun error(error: Throwable) = create(false, null, error, false)
        fun inFlight() = create(true, null, null, false)
        fun empty() = create(false, null, null, true)
    }
}