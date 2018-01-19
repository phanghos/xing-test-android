package com.taitascioredev.android.xingtest.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.taitascioredev.android.xingtest.domain.usecase.GetXingReposUseCase
import com.taitascioredev.android.xingtest.presentation.model.RepositoryListViewState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

/**
 * Created by rrtatasciore on 18/01/18.
 */
class RepositoryListViewModel(private val useCase: GetXingReposUseCase) : ViewModel() {

    private val stateSubject = PublishSubject.create<RepositoryListViewState>()

    private val stateLiveData = MutableLiveData<RepositoryListViewState>()

    private val disposables = CompositeDisposable()

    private var curState: RepositoryListViewState? = null

    override fun onCleared() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    fun getXingRepos(forceUpdate: Boolean = false) {
        if (curState != null && !forceUpdate) {
            stateLiveData.value = curState
            return
        }

        //disposables.add(useCase.getXingRepos().subscribe { stateSubject.onNext(it) })
        disposables.add(useCase.getXingRepos()
                .doOnNext { curState = it }
                .subscribe { stateLiveData.value = it})
    }

    fun states(): LiveData<RepositoryListViewState> = stateLiveData //Observable<RepositoryListViewState> = stateSubject
}