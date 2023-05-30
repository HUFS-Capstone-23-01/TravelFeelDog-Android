package com.example.travelfeeldog.data.model.gpt
import com.example.travelfeeldog.data.model.common.Header
import com.google.gson.annotations.SerializedName


data class GptSearchResultResponse(
    @SerializedName("body")
    val body: String,
    @SerializedName("header")
    val header: Header
)