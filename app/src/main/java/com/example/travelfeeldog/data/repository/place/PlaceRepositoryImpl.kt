package com.example.travelfeeldog.data.repository.place

import com.example.travelfeeldog.data.model.place.GetPlaceInfoResponse
import com.example.travelfeeldog.data.repository.place.datasource.PlaceDataSource

class PlaceRepositoryImpl(private val placeDataSource: PlaceDataSource): PlaceRepository {

    override suspend fun getPlaceInfo(authToken: String, placeId: Int): GetPlaceInfoResponse {
        return placeDataSource.getPlaceInfo(authToken, placeId)
    }
}