package com.example.travelfeeldog.data.network.api

import com.example.travelfeeldog.data.model.place.GetPlaceInfoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PlaceApi {

    @GET("place/{placeId}")
    suspend fun getPlaceInfo(
        @Header("Authorization") authToken: String,
        @Path("placeId") placeId: Int
    ): GetPlaceInfoResponse

}