package com.taitascioredev.android.xingtest.data.repository.impl

import com.google.gson.Gson
import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import com.taitascioredev.android.xingtest.data.repository.GithubRepository
import com.taitascioredev.android.xingtest.data.repository.datasource.CloudRepositoryDataStore
import com.taitascioredev.android.xingtest.data.repository.datasource.RepositoryDataStoreFactory
import com.taitascioredev.android.xingtest.fromJson
import com.taitascioredev.android.xingtest.presentation.model.RepositoryListViewState
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks


/**
 * Created by rrtatasciore on 20/01/18.
 */
class GithubRepositoryImplTest {

    @Mock lateinit var dataStoreFactory: RepositoryDataStoreFactory

    @Mock lateinit var cloudDataStore: CloudRepositoryDataStore

    lateinit var repository: GithubRepository

    @Before
    fun setUp() {
        initMocks(this)
        repository = GithubRepositoryImpl(dataStoreFactory)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun getXingRepos_should_return_success_state() {
        // Given
        val repo = Gson().fromJson<RepositoryEntity>("{}")
        val repos = ArrayList<RepositoryEntity>()
        repos.add(repo)
        val reposObservable = Observable.just<List<RepositoryEntity>>(repos)
        `when`(dataStoreFactory.create(1)).thenReturn(cloudDataStore)
        `when`(cloudDataStore.getXingRepos(1)).thenReturn(reposObservable)

        // When
        val resultObservable = repository.getXingRepos()

        // Then
        verify(dataStoreFactory).create(1)
        verify(cloudDataStore).getXingRepos(1)
        resultObservable.test().assertValueCount(2)
        resultObservable.test().assertValueAt(0, RepositoryListViewState.inFlight())
        resultObservable.test().assertValueAt(1, RepositoryListViewState.success(repos))
        resultObservable.test().assertComplete()
        resultObservable.test().assertNoErrors()
    }

    @Test
    fun getXingRepos_should_return_empty_state() {
        // Given
        val repos = ArrayList<RepositoryEntity>()
        val reposObservable = Observable.just<List<RepositoryEntity>>(repos)
        `when`(dataStoreFactory.create(1)).thenReturn(cloudDataStore)
        `when`(cloudDataStore.getXingRepos(1)).thenReturn(reposObservable)

        // When
        val resultObservable = repository.getXingRepos()

        // Then
        verify(dataStoreFactory).create(1)
        verify(cloudDataStore).getXingRepos(1)
        resultObservable.test().assertValueCount(2)
        resultObservable.test().assertValueAt(0, RepositoryListViewState.inFlight())
        resultObservable.test().assertValueAt(1, RepositoryListViewState.empty())
        resultObservable.test().assertComplete()
        resultObservable.test().assertNoErrors()
    }
}