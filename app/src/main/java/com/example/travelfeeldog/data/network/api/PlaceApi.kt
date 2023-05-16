package com.example.travelfeeldog.data.network.api

import com.example.travelfeeldog.data.model.place.GetPlaceInfoResponse
import com.example.travelfeeldog.data.model.place.MostReviewResponse
import com.example.travelfeeldog.data.model.place.PopularPlaceResponse
import com.example.travelfeeldog.data.model.review.PlaceReviewResponse
import com.example.travelfeeldog.data.model.search.PlaceSearchResultResponse
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

    @GET("place/recommend")
    suspend fun getPopularPlace(
        @Header("Authorization") authToken: String,
        @Query("categoryName") categoryName: String,
        @Query("locationName") locationName: String
    ): PopularPlaceResponse

    @GET("place/most/review")
    suspend fun getMostReviewPlace(
        @Header("Authorization") authToken: String,
        @Query("locationName") locationName: String
    ): MostReviewResponse

    @GET("place/search")
    suspend fun getSearchResult(
        @Header("Authorization") authToken: String,
        @Query("keyWord") keyword: String,
        @Query("categoryName") categoryName: String,
        @Query("locationName") locationName: String
    ): PlaceSearchResultResponse

}