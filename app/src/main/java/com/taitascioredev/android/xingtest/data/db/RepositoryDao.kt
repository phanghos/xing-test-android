package com.taitascioredev.android.xingtest.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.taitascioredev.android.xingtest.data.entity.RepositoryPageEntity

/**
 * Created by rrtatasciore on 20/01/18.
 */
@Dao
abstract class RepositoryDao {

    @Insert(onConflict = REPLACE)
    abstract fun insertRepos(repositoryPage: RepositoryPageEntity)

    @Query("SELECT * FROM repo_page WHERE page=:page")
    abstract fun getRepos(page: Int): RepositoryPageEntity
}