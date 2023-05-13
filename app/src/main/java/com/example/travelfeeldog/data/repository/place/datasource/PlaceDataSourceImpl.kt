package com.example.travelfeeldog.data.repository.place.datasource

import com.example.travelfeeldog.data.model.place.GetPlaceInfoResponse
import com.example.travelfeeldog.data.model.place.MostReviewResponse
import com.example.travelfeeldog.data.model.place.PopularPlaceResponse
import com.example.travelfeeldog.data.model.review.PlaceReviewResponse
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultRequest
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultResponse
import com.example.travelfeeldog.data.model.review.file.PostReviewFileResponse
import com.example.travelfeeldog.data.network.api.PlaceApi
import okhttp3.MultipartBody

class PlaceDataSourceImpl(private val placeApi: PlaceApi): PlaceDataSource {

    override suspend fun getPlaceInfo(authToken: String, placeId: Int): GetPlaceInfoResponse {
        return placeApi.getPlaceInfo(authToken, placeId)
    }
    override suspend fun getPlaceReview(authToken: String, placeId: Int): PlaceReviewResponse {
        return placeApi.getPlaceReview(authToken, placeId)
    }
    override suspend fun getPopularPlace(authToken: String, categoryName: String, locationName: String): PopularPlaceResponse {
        return placeApi.getPopularPlace(authToken, categoryName, locationName)
    }
    override suspend fun getMostReviewPlace(authToken: String, locationName: String): MostReviewResponse {
        return placeApi.getMostReviewPlace(authToken, locationName)
    }


}