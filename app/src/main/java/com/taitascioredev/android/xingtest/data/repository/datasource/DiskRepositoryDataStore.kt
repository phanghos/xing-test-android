package com.taitascioredev.android.xingtest.data.repository.datasource

import com.taitascioredev.android.xingtest.data.db.XingTestDb
import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import io.reactivex.Observable

/**
 * Created by rrtatasciore on 20/01/18.
 */
class DiskRepositoryDataStore(private val db: XingTestDb) : RepositoryDataStore {
    override fun getXingRepos(curPage: Int): Observable<List<RepositoryEntity>> {
        return Observable.just(db.repositoryDao().getRepos(curPage).repos)
    }
}