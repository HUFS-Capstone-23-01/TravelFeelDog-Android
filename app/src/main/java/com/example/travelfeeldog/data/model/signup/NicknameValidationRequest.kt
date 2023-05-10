package com.example.travelfeeldog.data.model.signup
import com.google.gson.annotations.SerializedName


data class NicknameValidationRequest(
    @SerializedName("nickName")
    val nickname: String
)