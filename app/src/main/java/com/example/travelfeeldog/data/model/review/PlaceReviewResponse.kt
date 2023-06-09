package com.example.travelfeeldog.data.model.review
import com.example.travelfeeldog.data.model.common.Header
import com.google.gson.annotations.SerializedName


data class PlaceReviewResponse(
    @SerializedName("body")
    val body: List<PlaceReview>,
    @SerializedName("header")
    val header: Header
)
