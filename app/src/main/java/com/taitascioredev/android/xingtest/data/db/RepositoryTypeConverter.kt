package com.taitascioredev.android.xingtest.data.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.taitascioredev.android.xingtest.data.entity.RepositoryEntity
import com.taitascioredev.android.xingtest.fromJson

/**
 * Created by rrtatasciore on 20/01/18.
 */
class RepositoryTypeConverter {

    @TypeConverter
    fun fromListToString(list: List<RepositoryEntity>): String = Gson().toJson(list)

    @TypeConverter
    fun fromStringToList(json: String): List<RepositoryEntity> = Gson().fromJson<List<RepositoryEntity>>(json)
}