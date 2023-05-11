package com.example.travelfeeldog.data.repository.place.datasource

import com.example.travelfeeldog.data.model.place.GetPlaceInfoResponse

interface PlaceDataSource {

    suspend fun getPlaceInfo(authToken: String, placeId: Int): GetPlaceInfoResponse
}