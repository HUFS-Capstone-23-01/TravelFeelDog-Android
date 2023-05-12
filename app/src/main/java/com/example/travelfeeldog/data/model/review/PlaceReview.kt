package com.example.travelfeeldog.data.model.review

import com.google.gson.annotations.SerializedName

data class PlaceReview(
    @SerializedName("additionalScript")
    val additionalScript: String,
    @SerializedName("createdDateTime")
    val createdDateTime: String,
    @SerializedName("imageUrls")
    val imageUrls: List<String>,
    @SerializedName("memberImageUrl")
    val memberImageUrl: String,
    @SerializedName("memberLevel")
    val memberLevel: Int,
    @SerializedName("memberNickname")
    val memberNickname: String,
    @SerializedName("recommendStatus")
    val recommendStatus: String
)
