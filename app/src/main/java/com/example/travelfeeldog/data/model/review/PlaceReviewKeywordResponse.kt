package com.example.travelfeeldog.data.model.review
import com.example.travelfeeldog.data.model.common.Header
import com.google.gson.annotations.SerializedName


data class PlaceReviewKeywordResponse(
    @SerializedName("body")
    val body: KeywordSet,
    @SerializedName("header")
    val header: Header
)

data class KeywordSet(
    @SerializedName("badKeyWords")
    val badKeyWords: List<BadKeyword>,
    @SerializedName("goodKeyWords")
    val goodKeyWords: List<GoodKeyword>
)

data class BadKeyword(
    @SerializedName("badKeyWordId")
    val badKeyWordId: Int,
    @SerializedName("badKeyWordName")
    val badKeyWordName: String
)

data class GoodKeyword(
    @SerializedName("goodKeyWordId")
    val goodKeyWordId: Int,
    @SerializedName("goodKeyWordName")
    val goodKeyWordName: String
)