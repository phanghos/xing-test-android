package com.taitascioredev.android.xingtest.data.repository.impl

import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import com.taitascioredev.android.xingtest.data.repository.GithubRepository
import com.taitascioredev.android.xingtest.data.repository.datasource.RepositoryDataStoreFactory
import com.taitascioredev.android.xingtest.presentation.model.RepositoryListViewState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrtatasciore on 18/01/18.
 */
class GithubRepositoryImpl @Inject constructor(private val factory: RepositoryDataStoreFactory) : GithubRepository {

    private var curPage = 1

    private var curList: ArrayList<RepositoryEntity>? = null

    override fun getXingRepos(): Observable<RepositoryListViewState> {
        return factory.create(curPage).getXingRepos(curPage)
                .map {
                    mergeResults(it)
                    if (it.isEmpty()) {
                        RepositoryListViewState.empty()
                    }
                    else {
                        curPage++
                        RepositoryListViewState.success(curList!!)
                    }
                }
                .onErrorReturn { RepositoryListViewState.error(it) }
                .startWith(RepositoryListViewState.inFlight())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun mergeResults(newList: List<RepositoryEntity>) {
        if (curList == null) {
            curList = ArrayList()
        }
        newList.forEach { curList?.add(it) }
    }
}