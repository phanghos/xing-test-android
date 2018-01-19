package com.taitascioredev.android.xingtest.data.net

import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rrtatasciore on 18/01/18.
 */
class GithubService @Inject constructor(private val api: GithubApi) {

    companion object {
        private val LIMIT = 10
    }

    fun getXingRepos(curPage: Int): Observable<List<RepositoryEntity>> {
        return api.getXingRepos(curPage, LIMIT)
    }
}