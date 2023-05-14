package com.example.travelfeeldog.data.repository.place

import com.example.travelfeeldog.data.model.place.GetPlaceInfoResponse
import com.example.travelfeeldog.data.model.place.MostReviewResponse
import com.example.travelfeeldog.data.model.place.PopularPlaceResponse
import com.example.travelfeeldog.data.model.review.PlaceReviewResponse
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultRequest
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultResponse
import com.example.travelfeeldog.data.model.review.file.PostReviewFileResponse
import com.example.travelfeeldog.data.repository.place.datasource.PlaceDataSource
import okhttp3.MultipartBody

class PlaceRepositoryImpl(private val placeDataSource: PlaceDataSource): PlaceRepository {

    override suspend fun getPlaceInfo(authToken: String, placeId: Int): GetPlaceInfoResponse {
        return placeDataSource.getPlaceInfo(authToken, placeId)
    }
    override suspend fun getPlaceReview(authToken: String, placeId: Int): PlaceReviewResponse {
        return placeDataSource.getPlaceReview(authToken, placeId)
    }

    override suspend fun getPopularPlace(authToken: String, categoryName: String, locationName: String): PopularPlaceResponse {
        return placeDataSource.getPopularPlace(authToken, categoryName, locationName)
    }

    override suspend fun getMostReviewPlace(authToken: String, locationName: String): MostReviewResponse {
        return placeDataSource.getMostReviewPlace(authToken, locationName)
    }
}