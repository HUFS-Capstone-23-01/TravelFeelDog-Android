package com.example.travelfeeldog.data.repository.sign.datasource

import com.example.travelfeeldog.data.model.signin.GetTokenValidResponse
import com.example.travelfeeldog.data.model.signin.PostMemberRequest
import com.example.travelfeeldog.data.model.signin.PostMemberResponse
import retrofit2.Call

interface AuthDataSource {

    suspend fun postMember(body: PostMemberRequest) : PostMemberResponse
    
    suspend fun getTokenValid(authToken: String) : GetTokenValidResponse
}