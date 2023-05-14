package com.example.travelfeeldog.data.model.search
import com.example.travelfeeldog.data.model.common.Header
import com.google.gson.annotations.SerializedName


data class PlaceSearchResultResponse(
    @SerializedName("body")
    val body: List<SearchingPlaceInfo>,
    @SerializedName("header")
    val header: Header
)

data class SearchingPlaceInfo(
    @SerializedName("address")
    val address: String,
    @SerializedName("goodKeyWords")
    val goodKeyWords: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbNailImageUrl")
    val thumbNailImageUrl: String
)