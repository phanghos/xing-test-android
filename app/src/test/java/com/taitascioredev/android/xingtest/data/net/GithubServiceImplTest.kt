package com.taitascioredev.android.xingtest.data.net

import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import com.taitascioredev.android.xingtest.data.net.impl.GithubServiceImpl
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks

/**
 * Created by rrtatasciore on 19/01/18.
 */
class GithubServiceImplTest {

    @Mock lateinit var api: GithubApi

    lateinit var service: GithubServiceImpl

    @Before
    fun setUp() {
        initMocks(this)
        service = GithubServiceImpl(api)
    }

    @Test
    fun getXingRepos_should_return_expected_value() {
        // Given
        val expectedValue: List<RepositoryEntity> = ArrayList()
        val expectedObservable = Observable.just(expectedValue)
        `when`(api.getXingRepos(anyInt(), anyInt())).thenReturn(expectedObservable)

        // When
        val resultObservable = service.getXingRepos(1)

        // Then
        verify(api).getXingRepos(1, 10)
        assertEquals(expectedObservable, resultObservable)
        resultObservable.test().assertValue(expectedValue)
        resultObservable.test().assertComplete()
        resultObservable.test().assertNoErrors()
    }
}