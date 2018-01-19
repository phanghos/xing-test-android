package com.taitascioredev.android.xingtest.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by rrtatasciore on 18/01/18.
 */
data class RepositoryEntity(
        val id: Int,
        val name: String,
        val description: String,
        val owner: OwnerEntity,
        @SerializedName("html_url") val htmlUrl: String
)