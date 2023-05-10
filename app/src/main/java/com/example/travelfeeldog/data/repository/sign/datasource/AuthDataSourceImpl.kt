package com.example.travelfeeldog.data.repository.sign.datasource

import com.example.travelfeeldog.data.model.signin.GetTokenValidResponse
import com.example.travelfeeldog.data.model.signup.NicknameValidationRequest
import com.example.travelfeeldog.data.model.signup.NicknameValidationResponse
import com.example.travelfeeldog.data.model.signup.PostMemberRequest
import com.example.travelfeeldog.data.model.signup.PostMemberResponse
import com.example.travelfeeldog.data.network.api.AuthApi
import com.google.firebase.auth.FirebaseUser

class AuthDataSourceImpl(private val authApi: AuthApi) : AuthDataSource {

    override suspend fun postMember(body: PostMemberRequest): PostMemberResponse {
        return authApi.postAccount(body)
    }

    override suspend fun getTokenValid(authToken: String): GetTokenValidResponse {
        return authApi.getTokenValid(authToken)
    }
    override suspend fun checkNicknameValidation(nickname: String): NicknameValidationResponse {
        return authApi.checkNicknameValidation(nickname)
    }
}