package com.example.travelfeeldog.data.model.signin
import com.example.travelfeeldog.data.model.common.Header
import com.google.gson.annotations.SerializedName

data class GetTokenValidResponse(
    @SerializedName("body")
    val body: Boolean,
    @SerializedName("header")
    val header: Header
)