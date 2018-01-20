package com.taitascioredev.android.xingtest.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by rrtatasciore on 20/01/18.
 */
@Entity(tableName = "repo_page")
class RepositoryPageEntity(
        val repos: List<RepositoryEntity>,
        val page: Int,
        @PrimaryKey(autoGenerate = true) var id: Int = 0
)