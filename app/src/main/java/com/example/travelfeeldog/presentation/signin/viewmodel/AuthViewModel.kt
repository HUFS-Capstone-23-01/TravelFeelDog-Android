package com.example.travelfeeldog.presentation.signin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelfeeldog.data.model.signin.SignInResponse
import com.example.travelfeeldog.data.model.signup.NicknameValidationRequest
import com.example.travelfeeldog.data.model.signup.PostMemberRequest
import com.example.travelfeeldog.data.repository.sign.AuthRepository
import com.example.travelfeeldog.util.Constants
import com.example.travelfeeldog.util.Event
import com.example.travelfeeldog.util.UserInfo
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import timber.log.Timber

class AuthViewModel(private val repository: AuthRepository): ViewModel() {

    private val _userLiveData = MutableLiveData<Event<FirebaseUser>>()
    val userLiveData: LiveData<Event<FirebaseUser>>
        get() = _userLiveData

    private val _isVerifiedUser = MutableLiveData<Event<Boolean>>()
    val isVerifiedUser: LiveData<Event<Boolean>>
        get() = _isVerifiedUser

    private val _isValidNickname = MutableLiveData<Boolean>()
    val isValidNickname: LiveData<Boolean>
        get() = _isValidNickname

    private val _isNicknameDuplicated = MutableLiveData<Boolean>()
    val isNicknameDuplicated: LiveData<Boolean>
        get() = _isNicknameDuplicated

    private val _isValidSignUp = MutableLiveData<Boolean>()
    val isValidSignUp: LiveData<Boolean>
        get() = _isValidSignUp

    private val _isSignedUp = MutableLiveData<Boolean>()
    val isSignedUp: LiveData<Boolean>
        get() = _isSignedUp

    // -------------------- 회원 가입 과정 --------------------
    fun postMember(nickname: String) {
        viewModelScope.launch {
            try{
                val requestData = PostMemberRequest(Firebase.auth.uid!! , nickname)
                val response = repository.postMember(requestData)
                if(response.header.status == 200) {
                    _isSignedUp.value = true
                    Timber.d("회원가입 성공 -> ${response.body}")
                } else {
                    _isSignedUp.value = false
                    Timber.d("회원가입 실패 -> ${response.body}")
                }
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }

    fun checkNicknameDuplicated(nickname: String) {
        viewModelScope.launch {
            try {
                val response = repository.checkNicknameValidation(nickname)
                if(response.header.status == 200) {
                    _isNicknameDuplicated.value = response.body
                    _isValidSignUp.value = _isNicknameDuplicated.value == false
                }
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }

    fun checkValidNickname(text: String) {
        _isValidNickname.value =  (text.length in Constants.nicknameMinLength..Constants.nicknameMaxLength) && (" " !in text)
        Timber.d("닉네임 유효성 검사 : ${_isValidNickname.value}")
    }

    // -------------------- 로그인 인증 과정 --------------------
    fun tryToSignInByAuth(authToken: String) {
        viewModelScope.launch {
            try {
                val response = repository.tryToSignInByAuth(authToken)
                Timber.d("Auth Token을 가지고 서버를 통한 로그인 시도 : ${response.header.status}")
                if (response.header.status == 200) {
                    _isVerifiedUser.value = Event(true)
                    UserInfo.setUserInfo(response.body!!)
                    Timber.d("저장된 유저 정보 : ${UserInfo.getUserInfo()}")
                } else{
                    _isVerifiedUser.value = Event(false)
                }
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }

    fun handleAuthToken(userInfo: FirebaseUser) {
        viewModelScope.launch {
            try {
                    _userLiveData.value = Event(userInfo)
                    Timber.d("파이어베이스에서 유저 토큰을 받았습니다.")
                } catch (e: Throwable) {
                    Timber.d(e)
                }
            }
        }
    }

