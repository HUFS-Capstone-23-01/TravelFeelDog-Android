package com.example.travelfeeldog.data.model.signup
import com.example.travelfeeldog.data.model.common.Header
import com.example.travelfeeldog.data.model.common.MemberInfoBody
import com.google.gson.annotations.SerializedName

data class PostMemberResponse(
    @SerializedName("body")
    val body: MemberInfoBody,
    @SerializedName("header")
    val header: Header
)
