package com.taitascioredev.android.xingtest.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.taitascioredev.android.xingtest.data.db.RepositoryTypeConverter

/**
 * Created by rrtatasciore on 18/01/18.
 */
@Entity(tableName = "repos")
@TypeConverters(RepositoryTypeConverter::class)
data class RepositoryEntity(
        @PrimaryKey(autoGenerate = true) var dbId: Int = 0,
        val id: Int,
        val name: String,
        val description: String?,
        @TypeConverters(OwnerEntity::class) val owner: OwnerEntity,
        @SerializedName("html_url") val htmlUrl: String,
        val fork: Boolean,
        val page: Int
)