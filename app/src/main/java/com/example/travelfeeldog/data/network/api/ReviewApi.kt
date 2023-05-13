package com.example.travelfeeldog.data.network.api

import com.example.travelfeeldog.data.model.review.PlaceReviewKeywordResponse
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultRequest
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultResponse
import com.example.travelfeeldog.data.model.review.file.PostReviewFileResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface ReviewApi {

    @Multipart
    @POST("file/images/url/{folderName}")
    suspend fun postReviewImage(
        @Path("folderName") folderName: String,
        @Part files : List<MultipartBody.Part>
    ): PostReviewFileResponse

    @POST("review")
    suspend fun postPlaceEvaluation(
        @Header("Authorization") authToken: String,
        @Body body: PlaceUserEvaluationResultRequest
    ): PlaceUserEvaluationResultResponse

    @GET("keyword/{categoryId}")
    suspend fun getPlaceKeyword(
        @Path("categoryId") categoryId: Int
    ): PlaceReviewKeywordResponse
}