package com.taitascioredev.android.xingtest.presentation.adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import com.taitascioredev.android.xingtest.R
import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by rrtatasciore on 18/01/18.
 */
class RepositoryAdapter(private val context: Context) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    private val repos: List<RepositoryEntity>

    private val longClickSubject = PublishSubject.create<RepositoryEntity>()

    init {
        repos = ArrayList()
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepositoryViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.repository_row_layout, parent, false)
        return RepositoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder?, position: Int) {
        val repo = repos[position]
        holder?.let {
            with (holder) {
                repoName.text = repo.name
                repoDescription.text = repo.description
                repoOwner.text = "by ${repo.owner.login}"
            }
            if (!repo.fork) {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.color_fork))
            } else {
                holder.itemView.setBackgroundColor(Color.WHITE)
            }
        }
    }

    fun add(repos: List<RepositoryEntity>, filter: Boolean = false) {
        Observable.fromIterable(ArrayList<RepositoryEntity>(repos))
                .filter { !filter || !this.repos.contains(it) }
                .subscribe {
                    (this.repos as ArrayList).add(it)
                    notifyItemInserted(itemCount)
                }
    }

    fun getLongClickObservable(): Observable<RepositoryEntity> = longClickSubject

    inner class RepositoryViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val repoName: TextView = v.findViewById(R.id.tv_repo_name)

        val repoDescription: TextView = v.findViewById(R.id.tv_repo_description)

        val repoOwner: TextView = v.findViewById(R.id.tv_repo_owner)

        init {
            RxView.longClicks(v).subscribe { longClickSubject.onNext(repos[adapterPosition]) }
        }
    }
}