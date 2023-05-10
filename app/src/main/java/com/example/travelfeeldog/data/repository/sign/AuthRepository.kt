package com.example.travelfeeldog.data.repository.sign

import android.provider.ContactsContract.CommonDataKinds.Nickname
import com.example.travelfeeldog.data.model.signin.GetTokenValidResponse
import com.example.travelfeeldog.data.model.signin.SignInResponse
import com.example.travelfeeldog.data.model.signup.NicknameValidationRequest
import com.example.travelfeeldog.data.model.signup.NicknameValidationResponse
import com.example.travelfeeldog.data.model.signup.PostMemberRequest
import com.example.travelfeeldog.data.model.signup.PostMemberResponse
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun postMember(body: PostMemberRequest) : PostMemberResponse
    suspend fun getTokenValid(authToken: String) : GetTokenValidResponse
    suspend fun getAuthTokenFromFirebase(googleIdToken: String): FirebaseUser?
    suspend fun checkNicknameValidation(nickname: String): NicknameValidationResponse
    suspend fun tryToSignInByAuth(authToken: String) : SignInResponse
}