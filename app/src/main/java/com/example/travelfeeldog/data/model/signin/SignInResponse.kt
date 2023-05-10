package com.example.travelfeeldog.data.model.signin

import com.example.travelfeeldog.data.model.common.Header
import com.example.travelfeeldog.data.model.common.MemberInfoBody
import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("header")
    val header: Header,
    @SerializedName("body")
    val body: MemberInfoBody?
)
