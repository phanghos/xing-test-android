package com.taitascioredev.android.xingtest.data.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.taitascioredev.android.xingtest.data.entity.OwnerEntity
import com.taitascioredev.android.xingtest.fromJson

/**
 * Created by rrtatasciore on 20/01/18.
 */
class OwnerTypeConverter {

    @TypeConverter
    fun fromObjectToJson(owner: OwnerEntity): String = Gson().toJson(owner)

    @TypeConverter
    fun fromJsonToObject(json: String): OwnerEntity = Gson().fromJson<OwnerEntity>(json)
}