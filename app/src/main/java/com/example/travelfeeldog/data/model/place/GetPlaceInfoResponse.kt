package com.example.travelfeeldog.data.model.place
import com.example.travelfeeldog.data.model.common.Header
import com.google.gson.annotations.SerializedName


data class GetPlaceInfoResponse(
    @SerializedName("body")
    val body: PlaceInfo,
    @SerializedName("header")
    val header: Header
)