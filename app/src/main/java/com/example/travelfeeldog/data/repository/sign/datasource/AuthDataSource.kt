package com.example.travelfeeldog.data.repository.sign.datasource

import com.example.travelfeeldog.data.model.signin.GetTokenValidResponse
import com.example.travelfeeldog.data.model.signup.NicknameValidationRequest
import com.example.travelfeeldog.data.model.signup.NicknameValidationResponse
import com.example.travelfeeldog.data.model.signup.PostMemberRequest
import com.example.travelfeeldog.data.model.signup.PostMemberResponse
import com.google.firebase.auth.FirebaseUser

interface AuthDataSource {

    suspend fun postMember(body: PostMemberRequest) : PostMemberResponse
    suspend fun getTokenValid(authToken: String) : GetTokenValidResponse
    suspend fun checkNicknameValidation(nickname: String): NicknameValidationResponse

}