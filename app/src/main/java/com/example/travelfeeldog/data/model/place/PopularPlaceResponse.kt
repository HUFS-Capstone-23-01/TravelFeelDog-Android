package com.example.travelfeeldog.data.model.place
import com.example.travelfeeldog.data.model.common.Header
import com.google.gson.annotations.SerializedName


data class PopularPlaceResponse(
    @SerializedName("body")
    val body: List<PopularPlace>,
    @SerializedName("header")
    val header: Header
)

data class PopularPlace(
    @SerializedName("id")
    val id: Int,
    @SerializedName("placeName")
    val placeName: String,
    @SerializedName("categoryName")
    val categoryName: String,
    @SerializedName("thumbNailImageUrl")
    val thumbNailImageUrl: String
)