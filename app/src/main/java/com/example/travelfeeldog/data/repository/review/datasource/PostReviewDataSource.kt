package com.example.travelfeeldog.data.repository.review.datasource

import com.example.travelfeeldog.data.model.review.PlaceReviewKeywordResponse
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultRequest
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultResponse
import com.example.travelfeeldog.data.model.review.file.PostReviewFileResponse
import okhttp3.MultipartBody

interface PostReviewDataSource {

    suspend fun postReviewImage(folderName: String, files: List<MultipartBody.Part>): PostReviewFileResponse

    suspend fun postPlaceEvaluation(authToken: String, body: PlaceUserEvaluationResultRequest): PlaceUserEvaluationResultResponse

    suspend fun getPlaceKeyword(categoryId: Int): PlaceReviewKeywordResponse

}