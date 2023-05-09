package com.example.travelfeeldog.data.model.signin
import com.google.gson.annotations.SerializedName


data class PostMemberRequest(
    @SerializedName("firebaseToken")
    val firebaseToken: String,
    @SerializedName("nickName")
    val memberNickName: String
)