package com.example.travelfeeldog.data.repository.sign

import androidx.lifecycle.LiveData
import com.example.travelfeeldog.data.model.signin.GetTokenValidResponse
import com.example.travelfeeldog.data.model.signin.PostMemberRequest
import com.example.travelfeeldog.data.model.signin.PostMemberResponse
import com.google.firebase.auth.FirebaseUser
import retrofit2.Call
import retrofit2.Response

interface AuthRepository {

    suspend fun postMember(body: PostMemberRequest) : PostMemberResponse

    suspend fun getTokenValid(authToken: String) : GetTokenValidResponse

    suspend fun getAuthTokenFromFirebase(googleIdToken: String): FirebaseUser?
}