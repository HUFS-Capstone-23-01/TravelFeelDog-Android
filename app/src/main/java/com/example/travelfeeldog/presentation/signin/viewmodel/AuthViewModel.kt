package com.example.travelfeeldog.presentation.signin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelfeeldog.data.model.signin.PostMemberRequest
import com.example.travelfeeldog.data.model.signin.PostMemberResponse
import com.example.travelfeeldog.data.repository.sign.AuthRepository
import com.example.travelfeeldog.data.repository.sign.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class AuthViewModel(private val repository: AuthRepository): ViewModel() {

    private val _userLiveData = MutableLiveData<FirebaseUser>()
    val userLiveData: LiveData<FirebaseUser>
        get() = _userLiveData

    private val _isVerifiedUser = MutableLiveData<Boolean>()
    val isVerifiedUser: LiveData<Boolean>
        get() = _isVerifiedUser

    // -------------------- 회원 가입 과정 --------------------
    fun postMember(authToken: String, nickname: String) {
        viewModelScope.launch {
            val requestData = PostMemberRequest(authToken, nickname)
            val response = repository.postMember(requestData)
            if(response.header.status == 200) {
                TODO("정상적으로 회원 등록되었으니 로그인 처리 바람")
            } else {
                TODO("회원 등록에 실패했으니 예외 처리 바람")
            }
        }
    }

    // -------------------- 로그인 인증 과정 --------------------
    fun getTokenValid(authToken: String) {
        viewModelScope.launch {
            val response = repository.getTokenValid(authToken)
            _isVerifiedUser.value = (response.header.status == 200)
        }
    }

    fun getAuthTokenFromFirebase(googleIdToken: String) {
        viewModelScope.launch {
            repository.getAuthTokenFromFirebase(googleIdToken)?.let { userInfo ->
                _userLiveData.value = userInfo
            }
        }
    }


}