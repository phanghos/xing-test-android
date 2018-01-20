package com.taitascioredev.android.xingtest.data.repository.datasource

import com.taitascioredev.android.xingtest.data.db.XingTestDb
import com.taitascioredev.android.xingtest.data.net.GithubService
import javax.inject.Inject

/**
 * Created by rrtatasciore on 20/01/18.
 */
open class RepositoryDataStoreFactory @Inject constructor(private val service: GithubService, private val db: XingTestDb) {
    open fun create(pageNumber: Int): RepositoryDataStore {
        var result = db.repositoryDao().getRepos(pageNumber)
        return if (result == null) {
            CloudRepositoryDataStore(service, db)
        } else {
            DiskRepositoryDataStore(db)
        }
    }
}