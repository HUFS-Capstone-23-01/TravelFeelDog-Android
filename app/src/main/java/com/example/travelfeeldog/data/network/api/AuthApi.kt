package com.example.travelfeeldog.data.network.api

import com.example.travelfeeldog.data.model.signin.GetTokenValidResponse
import com.example.travelfeeldog.data.model.signin.PostMemberRequest
import com.example.travelfeeldog.data.model.signin.PostMemberResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {

    @POST("member")
    suspend fun postAccount(
        @Body body: PostMemberRequest
    ) : PostMemberResponse

    @GET("member/valid")
    suspend fun getTokenValid(
        @Header("Authorization") authToken: String
    ) : GetTokenValidResponse
}