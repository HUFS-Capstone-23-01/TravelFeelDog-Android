package com.example.travelfeeldog.data.repository.sign

import com.example.travelfeeldog.data.model.signin.GetTokenValidResponse
import com.example.travelfeeldog.data.model.signin.SignInResponse
import com.example.travelfeeldog.data.model.signup.NicknameValidationRequest
import com.example.travelfeeldog.data.model.signup.NicknameValidationResponse
import com.example.travelfeeldog.data.model.signup.PostMemberRequest
import com.example.travelfeeldog.data.model.signup.PostMemberResponse
import com.example.travelfeeldog.data.repository.sign.datasource.AuthDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import timber.log.Timber

class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override suspend fun postMember(body: PostMemberRequest): PostMemberResponse {
        return authDataSource.postMember(body)
    }

    override suspend fun getTokenValid(authToken: String): GetTokenValidResponse {
        return authDataSource.getTokenValid(authToken)
    }

    override suspend fun checkNicknameValidation(nickname: String): NicknameValidationResponse {
        return authDataSource.checkNicknameValidation(nickname)
    }

    override suspend fun tryToSignInByAuth(authToken: String): SignInResponse {
        return authDataSource.tryToSignInByAuth(authToken)
    }

    override suspend fun getAuthTokenFromFirebase(googleIdToken: String): FirebaseUser? {
        var userInfo: FirebaseUser? = null
        val credential = GoogleAuthProvider.getCredential(googleIdToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful) {
                Timber.d("파이어베이스에서 유저 토큰을 받았다!")
                userInfo = firebaseAuth.currentUser
            } else {
                Timber.d("FAIL: get Auth token from firebase")
            }
        }
        return userInfo
    }

}