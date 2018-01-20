package com.taitascioredev.android.xingtest.data.repository.impl

import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import com.taitascioredev.android.xingtest.data.net.GithubService
import com.taitascioredev.android.xingtest.data.repository.GithubRepository
import com.taitascioredev.android.xingtest.presentation.model.RepositoryListViewState
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import java.util.*
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import org.mockito.Mockito.*


/**
 * Created by rrtatasciore on 20/01/18.
 */
class GithubRepositoryImplTest {

    @Mock lateinit var service: GithubService

    lateinit var repository: GithubRepository

    @Before
    fun setUp() {
        initMocks(this)
        repository = GithubRepositoryImpl(service)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun getXingRepos_return_expected_value() {
        // Given
        val repos: List<RepositoryEntity> = ArrayList()
        val reposObservable = Observable.just<List<RepositoryEntity>>(repos)
        `when`(service.getXingRepos(1)).thenReturn(reposObservable)

        // When
        val resultObservable = repository.getXingRepos()

        // Then
        verify(service).getXingRepos(1)
        resultObservable.test().assertValueAt(0, RepositoryListViewState.inFlight())
        resultObservable.test().assertValueAt(1, RepositoryListViewState.success(repos))
        resultObservable.test().assertComplete()
        resultObservable.test().assertNoErrors()
    }
}