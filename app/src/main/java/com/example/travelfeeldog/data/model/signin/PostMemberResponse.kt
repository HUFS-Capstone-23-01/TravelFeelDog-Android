package com.example.travelfeeldog.data.model.signin
import com.example.travelfeeldog.data.model.Header
import com.google.gson.annotations.SerializedName

data class PostMemberResponse(
    @SerializedName("body")
    val body: MemberInfoBody,
    @SerializedName("header")
    val header: Header
)

data class MemberInfoBody(
    @SerializedName("exp")
    val exp: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("level")
    val level: Int,
    @SerializedName("nickName")
    val nickName: String,
    @SerializedName("token")
    val token: String
)