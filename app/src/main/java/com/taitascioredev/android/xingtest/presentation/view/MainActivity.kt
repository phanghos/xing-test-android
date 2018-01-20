package com.taitascioredev.android.xingtest.presentation.view

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView
import com.jakewharton.rxbinding2.view.RxView
import com.taitascioredev.android.xingtest.R
import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import com.taitascioredev.android.xingtest.navigateToUrl
import com.taitascioredev.android.xingtest.presentation.adapter.RepositoryAdapter
import com.taitascioredev.android.xingtest.presentation.model.RepositoryListViewState
import com.taitascioredev.android.xingtest.presentation.viewmodel.RepositoryListViewModel
import com.taitascioredev.android.xingtest.presentation.viewmodel.RepositoryListViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasActivityInjector {

    companion object {
        private val UPDATE_OFFSET = 2
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }

    private var isListUpdating = false

    @Inject lateinit var factory: RepositoryListViewModelFactory

    @Inject lateinit var injector: DispatchingAndroidInjector<Activity>

    private var adapter: RepositoryAdapter? = null

    private val viewModel: RepositoryListViewModel by lazy {
        ViewModelProviders.of(this, factory).get(RepositoryListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        list.isNestedScrollingEnabled = true
        AndroidInjection.inject(this)
        viewModel.states().observe(this, Observer { render(it) })
        viewModel.getXingRepos()
        bindUiEvents()
    }

    private fun bindUiEvents() {
        RxView.clicks(btn_retry).subscribe { viewModel.getXingRepos(true) }

        RxRecyclerView.scrollEvents(list)
                .filter {
                    val layoutManager = list.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                    visibleItemCount + pastVisibleItems + UPDATE_OFFSET >= totalItemCount && !isListUpdating
                }
                .doOnNext { isListUpdating = true }
                .subscribe { viewModel.getXingRepos(true) }
    }

    private fun render(state: RepositoryListViewState?) {
        if (!state?.loading()!!) {
            isListUpdating = false
        }

        state?.let {
            when {
                state.loading() && !isListUpdating -> renderLoading()
                state.repos() != null -> renderRepos(state.repos()!!)
                state.error() != null -> renderError(state.error()!!)
            }
        }
    }

    private fun renderLoading() {
        progress_wheel.visibility = View.VISIBLE
        btn_retry.visibility = View.GONE
    }

    private fun renderRepos(repos: List<RepositoryEntity>) {
        list.visibility = View.VISIBLE
        progress_wheel.visibility = View.GONE
        btn_retry.visibility = View.GONE

        if (adapter == null) {
            adapter = RepositoryAdapter(repos)
            list.adapter = adapter
            adapter?.getLongClickObservable()?.subscribe { showDialog(it) }
        } else {
            adapter?.add(repos)
        }
    }

    private fun renderError(error: Throwable) {
        btn_retry.visibility = View.VISIBLE
        progress_wheel.visibility = View.GONE
        list.visibility = View.GONE
    }

    private fun showDialog(repo: RepositoryEntity) {
        AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("What page do you want to open?")
                .setItems(arrayOf("Repo page", "Owner page"), { _, which ->
                    when (which) {
                        0 -> navigateToUrl(repo.htmlUrl)
                        1 -> navigateToUrl(repo.owner.htmlUrl)
                    }
                })
                .show()
    }
}
