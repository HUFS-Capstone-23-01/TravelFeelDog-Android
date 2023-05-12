package com.example.travelfeeldog.data.model.place

import com.google.gson.annotations.SerializedName

data class PlaceInfo(
    @SerializedName("address")
    val address: String,
    @SerializedName("describe")
    val describe: String,
    @SerializedName("facilityNames")
    val facilityNames: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("largeDogBadTotal")
    val largeDogBadTotal: Int,
    @SerializedName("largeDogGoodTotal")
    val largeDogGoodTotal: Int,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("mediumDogBadTotal")
    val mediumDogBadTotal: Int,
    @SerializedName("mediumDogGoodTotal")
    val mediumDogGoodTotal: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("reviewCountBad")
    val reviewCountBad: Int,
    @SerializedName("reviewCountGood")
    val reviewCountGood: Int,
    @SerializedName("reviewCountIdk")
    val reviewCountIdk: Int,
    @SerializedName("smallDogBadTotal")
    val smallDogBadTotal: Int,
    @SerializedName("smallDogGoodTotal")
    val smallDogGoodTotal: Int,
    @SerializedName("thumbNailImageUrl")
    val thumbNailImageUrl: String,
    @SerializedName("reviewCount")
    val reviewCount: Int,
    @SerializedName("categoryId")
    val categoryId: Int
)
