package com.taitascioredev.android.xingtest.domain.usecase.impl

import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import com.taitascioredev.android.xingtest.data.repository.GithubRepository
import com.taitascioredev.android.xingtest.domain.usecase.GetXingReposUseCase
import com.taitascioredev.android.xingtest.presentation.model.RepositoryListViewState
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks

/**
 * Created by rrtatasciore on 20/01/18.
 */
class GetXingReposUseCaseImplTest {

    @Mock lateinit var repository: GithubRepository

    lateinit var useCase: GetXingReposUseCase

    @Before
    fun setUp() {
        initMocks(this)
        useCase = GetXingReposUseCaseImpl(repository)
    }

    @Test
    fun getXingRepos_return_expected_value() {
        // Given
        val repos: List<RepositoryEntity> = ArrayList()
        val expectedValue = RepositoryListViewState.success(repos)
        val expectedObservable = Observable.just(expectedValue)
        `when`(repository.getXingRepos()).thenReturn(expectedObservable)

        // When
        val resultObservable = useCase.getXingRepos()

        // Then
        verify(repository).getXingRepos()
        resultObservable.test().assertValue(expectedValue)
        resultObservable.test().assertComplete()
        resultObservable.test().assertNoErrors()
    }
}