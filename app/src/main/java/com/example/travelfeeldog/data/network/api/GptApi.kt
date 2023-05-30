package com.example.travelfeeldog.data.network.api

import com.example.travelfeeldog.data.model.gpt.GptSearchResultResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GptApi {

    @GET("place/search/gpt")
    suspend fun getGptSearchResult(
        @Header("Authorization") authToken: String,
        @Query("question") userInput: String
    ): GptSearchResultResponse
}