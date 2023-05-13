package com.example.travelfeeldog.data.model.review
import com.google.gson.annotations.SerializedName


data class PlaceUserEvaluationResultRequest(
    @SerializedName("placeId")
    val placeId: Int,
    @SerializedName("recommendStatus")
    val recommendStatus: String,
    @SerializedName("smallDogNumber")
    val smallDogNumber: Int,
    @SerializedName("mediumDogNumber")
    val mediumDogNumber: Int,
    @SerializedName("largeDogNumber")
    val largeDogNumber: Int,
    @SerializedName("goodKeyWordIds")
    val goodKeyWordIds: List<Int>,
    @SerializedName("badKeyWordIds")
    val badKeyWordIds: List<Int>,
    @SerializedName("additionalScript")
    val additionalScript: String,
    @SerializedName("imageUrls")
    val imageUrls: List<String>
)