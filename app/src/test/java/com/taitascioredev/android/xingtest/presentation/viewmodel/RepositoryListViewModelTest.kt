package com.taitascioredev.android.xingtest.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import com.taitascioredev.android.xingtest.domain.usecase.GetXingReposUseCase
import com.taitascioredev.android.xingtest.presentation.model.RepositoryListViewState
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks

/**
 * Created by rrtatasciore on 20/01/18.
 */
class RepositoryListViewModelTest {

    @Mock lateinit var useCase: GetXingReposUseCase

    @Mock lateinit var observer: Observer<RepositoryListViewState>

    lateinit var viewModel: RepositoryListViewModel

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        initMocks(this)
        viewModel = RepositoryListViewModel(useCase)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun getXingRepos_should_update_livedata_with_expected_value() {
        // Given
        val repos = ArrayList<RepositoryEntity>()
        val expectedValue = RepositoryListViewState.success(repos)
        val expectedObservable = Observable.just(expectedValue)
        `when`(useCase.getXingRepos()).thenReturn(expectedObservable)

        // When
        viewModel.states().observeForever(observer)
        viewModel.getXingRepos(true)

        // Then
        verify(useCase).getXingRepos()
        verify(observer).onChanged(expectedValue)
        assertNotNull(viewModel.states().value)
        assertEquals(viewModel.states().value, expectedValue)
    }

    @Test
    fun getXingRepos_should_not_fire_request() {
        // Given
        val repos = ArrayList<RepositoryEntity>()
        val expectedValue = RepositoryListViewState.success(repos)
        viewModel.setCurrentState(expectedValue)

        // When
        viewModel.getXingRepos()

        // Then
        verifyZeroInteractions(useCase)
    }

    @Test
    fun getXingRepos_should_force_network() {
        // Given
        val repos = ArrayList<RepositoryEntity>()
        val expectedValue = RepositoryListViewState.success(repos)
        val expectedObservable = Observable.just(expectedValue)
        viewModel.setCurrentState(expectedValue)
        `when`(useCase.getXingRepos()).thenReturn(expectedObservable)

        // When
        viewModel.states().observeForever(observer)
        viewModel.getXingRepos(true)

        // Then
        verify(useCase).getXingRepos()
        verify(observer).onChanged(expectedValue)
        assertNotNull(viewModel.states().value)
        assertEquals(viewModel.states().value, expectedValue)
    }
}