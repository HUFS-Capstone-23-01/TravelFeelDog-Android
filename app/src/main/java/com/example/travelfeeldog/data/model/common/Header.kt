package com.example.travelfeeldog.data.model.common

import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)
