package com.example.travelfeeldog.data.model.review

import com.example.travelfeeldog.data.model.common.Header
import com.google.gson.annotations.SerializedName

data class PlaceUserEvaluationResultResponse(
    @SerializedName("header")
    val header: Header,
    @SerializedName("body")
    val body: PlaceReview
)
