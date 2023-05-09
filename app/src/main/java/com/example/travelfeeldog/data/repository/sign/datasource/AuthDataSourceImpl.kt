package com.example.travelfeeldog.data.repository.sign.datasource

import com.example.travelfeeldog.data.model.signin.GetTokenValidResponse
import com.example.travelfeeldog.data.model.signin.PostMemberRequest
import com.example.travelfeeldog.data.model.signin.PostMemberResponse
import com.example.travelfeeldog.data.network.api.AuthApi
import retrofit2.Call

class AuthDataSourceImpl(private val authApi: AuthApi) : AuthDataSource {

    override suspend fun postMember(body: PostMemberRequest): PostMemberResponse {
        return authApi.postAccount(body)
    }

    override suspend fun getTokenValid(authToken: String): GetTokenValidResponse {
        return authApi.getTokenValid(authToken)
    }
}