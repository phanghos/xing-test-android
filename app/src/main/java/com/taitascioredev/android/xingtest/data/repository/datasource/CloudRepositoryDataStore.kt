package com.taitascioredev.android.xingtest.data.repository.datasource

import com.taitascioredev.android.xingtest.data.db.XingTestDb
import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import com.taitascioredev.android.xingtest.data.entity.RepositoryPageEntity
import com.taitascioredev.android.xingtest.data.net.GithubService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rrtatasciore on 20/01/18.
 */
open class CloudRepositoryDataStore @Inject constructor(private val service: GithubService, private val db: XingTestDb) : RepositoryDataStore {
    override fun getXingRepos(curPage: Int): Observable<List<RepositoryEntity>> {
        return service.getXingRepos(curPage)
                .doOnNext {
                    db.repositoryDao().insertRepos(RepositoryPageEntity(it, curPage))
                }
    }
}