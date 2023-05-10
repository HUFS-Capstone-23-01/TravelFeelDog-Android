package com.example.travelfeeldog.data.model.signup
import com.example.travelfeeldog.data.model.common.Header
import com.google.gson.annotations.SerializedName


data class NicknameValidationResponse(
    @SerializedName("body")
    val body: Boolean,
    @SerializedName("header")
    val header: Header
)