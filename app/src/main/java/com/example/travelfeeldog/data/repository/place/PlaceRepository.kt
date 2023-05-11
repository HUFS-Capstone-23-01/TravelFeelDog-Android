package com.example.travelfeeldog.data.repository.place

import com.example.travelfeeldog.data.model.place.GetPlaceInfoResponse

interface PlaceRepository {

    suspend fun getPlaceInfo(authToken: String, placeId: Int): GetPlaceInfoResponse
}