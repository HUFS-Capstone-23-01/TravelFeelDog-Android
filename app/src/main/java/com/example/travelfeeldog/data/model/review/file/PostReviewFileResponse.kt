package com.example.travelfeeldog.data.model.review.file
import com.example.travelfeeldog.data.model.common.Header
import com.google.gson.annotations.SerializedName


data class PostReviewFileResponse(
    @SerializedName("body")
    val body: List<FileUrl>,
    @SerializedName("header")
    val header: Header
)

data class FileUrl(
    @SerializedName("fileUrls")
    val fileUrls: String
)