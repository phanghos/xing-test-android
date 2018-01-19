package com.taitascioredev.android.xingtest.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by rrtatasciore on 18/01/18.
 */
data class OwnerEntity(
        val login: String,
        @SerializedName("html_url") val htmlUrl: String
)