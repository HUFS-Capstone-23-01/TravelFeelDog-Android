package com.example.travelfeeldog.data.repository.review.datasource

import com.example.travelfeeldog.data.model.review.PlaceReviewKeywordResponse
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultRequest
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultResponse
import com.example.travelfeeldog.data.model.review.file.PostReviewFileResponse
import com.example.travelfeeldog.data.network.api.ReviewApi
import okhttp3.MultipartBody

class PostReviewDataSourceImpl(private val reviewApi: ReviewApi): PostReviewDataSource {

    override suspend fun postReviewImage(folderName: String, files: List<MultipartBody.Part>): PostReviewFileResponse {
        return reviewApi.postReviewImage(folderName, files)
    }

    override suspend fun postPlaceEvaluation(authToken: String, body: PlaceUserEvaluationResultRequest): PlaceUserEvaluationResultResponse {
        return reviewApi.postPlaceEvaluation(authToken, body)
    }

    override suspend fun getPlaceKeyword(categoryId: Int): PlaceReviewKeywordResponse {
        return reviewApi.getPlaceKeyword(categoryId)
    }
}