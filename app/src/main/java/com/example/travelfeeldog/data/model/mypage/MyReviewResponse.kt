package com.example.travelfeeldog.data.model.mypage
import com.example.travelfeeldog.data.model.common.Header
import com.google.gson.annotations.SerializedName


data class MyReviewResponse(
    @SerializedName("body")
    val body: List<MyReviewList>,
    @SerializedName("header")
    val header: Header
)

data class MyReviewList(
    @SerializedName("additionalScript")
    val additionalScript: String,
    @SerializedName("createdDateTime")
    val createdDateTime: String,
    @SerializedName("imageUrls")
    val imageUrls: List<String>,
    @SerializedName("placeName")
    val placeName: String,
    @SerializedName("recommendStatus")
    val recommendStatus: String
)