package com.example.travelfeeldog.data.model.common

import com.google.gson.annotations.SerializedName

data class MemberInfoBody(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nickName")
    val nickName: String,
    @SerializedName("level")
    val level: Int,
    @SerializedName("exp")
    val exp: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("token")
    val token: String
)
