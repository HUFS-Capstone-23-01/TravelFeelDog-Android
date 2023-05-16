package com.example.travelfeeldog.data.model.place
import com.example.travelfeeldog.data.model.common.Header
import com.google.gson.annotations.SerializedName


data class PlaceKeywordStatisticsResponse(
    @SerializedName("body")
    val body: StatisticsBody,
    @SerializedName("header")
    val header: Header
)

data class StatisticsBody(
    @SerializedName("keyWords")
    val keyWords: List<KeywordStatistics>
)

data class KeywordStatistics(
    @SerializedName("keyWordCount")
    val keyWordCount: Int,
    @SerializedName("keyWordId")
    val keyWordId: Int,
    @SerializedName("keyWordName")
    val keyWordName: String
)