package com.taitascioredev.android.xingtest.data.net

import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import io.reactivex.Observable

/**
 * Created by rrtatasciore on 20/01/18.
 */
interface GithubService {
    fun getXingRepos(curPage: Int): Observable<List<RepositoryEntity>>
}