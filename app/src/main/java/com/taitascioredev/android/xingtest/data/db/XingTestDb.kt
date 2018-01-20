package com.taitascioredev.android.xingtest.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import com.taitascioredev.android.xingtest.data.entity.RepositoryPageEntity

/**
 * Created by rrtatasciore on 20/01/18.
 */
@Database(entities = [RepositoryEntity::class, RepositoryPageEntity::class], version = 1, exportSchema = false)
@TypeConverters(RepositoryTypeConverter::class, OwnerTypeConverter::class)
abstract class XingTestDb : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}