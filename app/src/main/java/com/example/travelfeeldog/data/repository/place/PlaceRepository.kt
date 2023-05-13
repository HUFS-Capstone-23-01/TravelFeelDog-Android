package com.example.travelfeeldog.data.repository.place

import com.example.travelfeeldog.data.model.place.GetPlaceInfoResponse
import com.example.travelfeeldog.data.model.review.PlaceReviewResponse
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultRequest
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultResponse
import com.example.travelfeeldog.data.model.review.file.PostReviewFileResponse
import okhttp3.MultipartBody

interface PlaceRepository {
    suspend fun getPlaceInfo(authToken: String, placeId: Int): GetPlaceInfoResponse
    suspend fun getPlaceReview(authToken: String, placeId: Int): PlaceReviewResponse
}