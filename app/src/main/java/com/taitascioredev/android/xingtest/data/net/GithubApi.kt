package com.taitascioredev.android.xingtest.data.net

import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rrtatasciore on 18/01/18.
 */
interface GithubApi {

    companion object {
        val BASE_URL = "https://api.github.com/"
    }

    @GET("users/xing/repos")
    fun getXingRepos(@Query("page") page: Int, @Query("per_page") limit: Int): Observable<List<RepositoryEntity>>
}