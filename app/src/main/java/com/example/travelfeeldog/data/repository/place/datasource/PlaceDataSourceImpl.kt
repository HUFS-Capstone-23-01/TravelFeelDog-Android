package com.example.travelfeeldog.data.repository.place.datasource

import com.example.travelfeeldog.data.model.place.GetPlaceInfoResponse
import com.example.travelfeeldog.data.model.review.PlaceReviewResponse
import com.example.travelfeeldog.data.network.api.PlaceApi

class PlaceDataSourceImpl(private val placeApi: PlaceApi): PlaceDataSource {

    override suspend fun getPlaceInfo(authToken: String, placeId: Int): GetPlaceInfoResponse {
        return placeApi.getPlaceInfo(authToken, placeId)
    }

    override suspend fun getPlaceReview(authToken: String, placeId: Int): PlaceReviewResponse {
        return placeApi.getPlaceReview(authToken, placeId)
    }
}