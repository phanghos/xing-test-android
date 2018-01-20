package com.taitascioredev.android.xingtest.data.net.impl

import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import com.taitascioredev.android.xingtest.data.net.GithubApi
import com.taitascioredev.android.xingtest.data.net.GithubService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rrtatasciore on 18/01/18.
 */
class GithubServiceImpl @Inject constructor(private val api: GithubApi) : GithubService {

    companion object {
        private val LIMIT = 10
    }

    override fun getXingRepos(curPage: Int): Observable<List<RepositoryEntity>> {
        return api.getXingRepos(curPage, LIMIT)
    }
}