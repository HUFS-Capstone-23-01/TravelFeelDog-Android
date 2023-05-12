package com.example.travelfeeldog.data.network.api

import com.example.travelfeeldog.data.model.place.GetPlaceInfoResponse
import com.example.travelfeeldog.data.model.review.PlaceReviewResponse
import retrofit2.http.*

interface PlaceApi {

    @GET("place/{placeId}")
    suspend fun getPlaceInfo(
        @Header("Authorization") authToken: String,
        @Path("placeId") placeId: Int
    ): GetPlaceInfoResponse

    @GET("review/places/{placeId}")
    suspend fun getPlaceReview(
        @Header("Authorization") authToken: String,
        @Path("placeId") placeId: Int
    ): PlaceReviewResponse

}