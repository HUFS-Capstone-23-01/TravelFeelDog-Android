package com.example.travelfeeldog.data.model.review
import com.example.travelfeeldog.data.model.common.Header
import com.google.gson.annotations.SerializedName


data class PlaceReviewResponse(
    @SerializedName("body")
    val body: List<PlaceReview>,
    @SerializedName("header")
    val header: Header
)

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