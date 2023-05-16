package com.example.travelfeeldog.data.repository.place

import com.example.travelfeeldog.data.model.place.GetPlaceInfoResponse
import com.example.travelfeeldog.data.model.place.MostReviewResponse
import com.example.travelfeeldog.data.model.place.PopularPlaceResponse
import com.example.travelfeeldog.data.model.review.PlaceReviewResponse
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultRequest
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultResponse
import com.example.travelfeeldog.data.model.review.file.PostReviewFileResponse
import com.example.travelfeeldog.data.model.search.PlaceSearchResultResponse
import okhttp3.MultipartBody

interface PlaceRepository {
    suspend fun getPlaceInfo(authToken: String, placeId: Int): GetPlaceInfoResponse
    suspend fun getPlaceReview(authToken: String, placeId: Int): PlaceReviewResponse
    suspend fun getPopularPlace(authToken: String, categoryName: String, locationName: String): PopularPlaceResponse
    suspend fun getMostReviewPlace(authToken: String, locationName: String): MostReviewResponse
    suspend fun getSearchResult(authToken: String, keyword: String, categoryName: String, locationName: String): PlaceSearchResultResponse

}