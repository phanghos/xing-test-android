package com.taitascioredev.android.xingtest.data.repository.datasource

import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import io.reactivex.Observable

/**
 * Created by rrtatasciore on 20/01/18.
 */
interface RepositoryDataStore {
    fun getXingRepos(curPage: Int): Observable<List<RepositoryEntity>>
}