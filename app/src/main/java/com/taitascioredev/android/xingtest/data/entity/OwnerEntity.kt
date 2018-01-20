package com.taitascioredev.android.xingtest.data.entity

import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.taitascioredev.android.xingtest.data.db.OwnerTypeConverter

/**
 * Created by rrtatasciore on 18/01/18.
 */
@TypeConverters(OwnerTypeConverter::class)
data class OwnerEntity(
        val login: String,
        @SerializedName("html_url") val htmlUrl: String
)