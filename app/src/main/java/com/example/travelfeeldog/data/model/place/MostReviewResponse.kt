package com.example.travelfeeldog.data.model.place
import com.example.travelfeeldog.data.model.common.Header
import com.google.gson.annotations.SerializedName


data class MostReviewResponse(
    @SerializedName("body")
    val body: List<MostReviewPlace>,
    @SerializedName("header")
    val header: Header
)

data class MostReviewPlace(
    @SerializedName("id")
    val id: Int,
    @SerializedName("placeName")
    val placeName: String,
    @SerializedName("categoryName")
    val categoryName: String,
    @SerializedName("reviews")
    val reviews: List<ReviewInfo>,
    @SerializedName("thumbNailImageUrl")
    val thumbNailImageUrl: String
)

data class ReviewInfo(
    @SerializedName("additionalScript")
    val additionalScript: String,
    @SerializedName("nickName")
    val nickName: String
)