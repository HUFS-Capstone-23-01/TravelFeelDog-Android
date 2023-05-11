package com.example.travelfeeldog.data.repository.place.datasource

import com.example.travelfeeldog.data.model.place.GetPlaceInfoResponse
import com.example.travelfeeldog.data.network.api.PlaceApi

class PlaceDataSourceImpl(private val placeApi: PlaceApi): PlaceDataSource {

    override suspend fun getPlaceInfo(authToken: String, placeId: Int): GetPlaceInfoResponse {
        return placeApi.getPlaceInfo(authToken, placeId)
    }
}