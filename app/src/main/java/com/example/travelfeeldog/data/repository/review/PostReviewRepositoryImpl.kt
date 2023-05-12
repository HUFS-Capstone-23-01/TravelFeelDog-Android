package com.example.travelfeeldog.data.repository.review

import com.example.travelfeeldog.data.model.review.PlaceReviewKeywordResponse
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultRequest
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultResponse
import com.example.travelfeeldog.data.model.review.file.PostReviewFileResponse
import com.example.travelfeeldog.data.repository.review.datasource.PostReviewDataSource
import okhttp3.MultipartBody

class PostReviewRepositoryImpl(private val postReviewDataSource: PostReviewDataSource): PostReviewRepository {

    override suspend fun postReviewImage(folderName: String, files: List<MultipartBody.Part>): PostReviewFileResponse {
        return postReviewDataSource.postReviewImage(folderName, files)
    }

    override suspend fun postPlaceEvaluation(authToken: String, body: PlaceUserEvaluationResultRequest): PlaceUserEvaluationResultResponse {
        return postReviewDataSource.postPlaceEvaluation(authToken, body)
    }

    override suspend fun getPlaceKeyword(categoryId: Int): PlaceReviewKeywordResponse {
        return postReviewDataSource.getPlaceKeyword(categoryId)
    }
}