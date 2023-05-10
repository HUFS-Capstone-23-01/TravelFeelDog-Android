package com.example.travelfeeldog.data.network.api

import com.example.travelfeeldog.data.model.signin.GetTokenValidResponse
import com.example.travelfeeldog.data.model.signin.SignInResponse
import com.example.travelfeeldog.data.model.signup.NicknameValidationRequest
import com.example.travelfeeldog.data.model.signup.NicknameValidationResponse
import com.example.travelfeeldog.data.model.signup.PostMemberRequest
import com.example.travelfeeldog.data.model.signup.PostMemberResponse
import retrofit2.http.*

interface AuthApi {

    @POST("member")
    suspend fun postAccount(
        @Body body: PostMemberRequest
    ) : PostMemberResponse

    @GET("member/valid")
    suspend fun getTokenValid(
        @Header("Authorization") authToken: String
    ) : GetTokenValidResponse


    @GET("member/profile/nick/valid")
    suspend fun checkNicknameValidation(
        @Query("nickName") nickname: String
    ) : NicknameValidationResponse

    @GET("member")
    suspend fun tryToSignInByAuth(
        @Header("Authorization") authToken: String
    ) : SignInResponse
}
