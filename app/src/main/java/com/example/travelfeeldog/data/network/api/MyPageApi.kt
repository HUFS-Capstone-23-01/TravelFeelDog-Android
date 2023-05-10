package com.example.travelfeeldog.data.network.api

import com.example.travelfeeldog.data.model.mypage.MyReviewResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface MyPageApi {

    @GET("review/member/page")
    suspend fun getMyReviewList(
        @Header("Authorization") authToken: String
    ): MyReviewResponse

}