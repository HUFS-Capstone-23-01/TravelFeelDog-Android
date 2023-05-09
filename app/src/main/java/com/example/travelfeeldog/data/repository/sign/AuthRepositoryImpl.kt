package com.example.travelfeeldog.data.repository.sign

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.travelfeeldog.data.model.signin.GetTokenValidResponse
import com.example.travelfeeldog.data.model.signin.PostMemberRequest
import com.example.travelfeeldog.data.model.signin.PostMemberResponse
import com.example.travelfeeldog.data.repository.sign.datasource.AuthDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override suspend fun postMember(body: PostMemberRequest): PostMemberResponse {
        return authDataSource.postMember(body)
    }

    override suspend fun getTokenValid(authToken: String): GetTokenValidResponse {
        return authDataSource.getTokenValid(authToken)
    }

    override suspend fun getAuthTokenFromFirebase(googleIdToken: String): FirebaseUser? {
        var userInfo: FirebaseUser? = null
        val credential = GoogleAuthProvider.getCredential(googleIdToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful) {
                userInfo = firebaseAuth.currentUser
            } else {
                Timber.d("FAIL: get Auth token from firebase")
            }
        }
        return userInfo
    }
}