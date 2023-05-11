package com.example.travelfeeldog.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelfeeldog.data.model.common.MemberInfoBody
import com.example.travelfeeldog.data.repository.user.UserRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class UserViewModel(private val repository: UserRepository): ViewModel() {

    private val _userInfo = MutableLiveData<MemberInfoBody>()
    val userInfo: LiveData<MemberInfoBody>
        get() = _userInfo

    fun getUserInfoByNickname(nickname: String) {
        viewModelScope.launch {
            try {
                val response = repository.getUserInfoByNickname(nickname)
                if(response.header.status == 200) {
                    _userInfo.value = response.body!!
                    Timber.d("유저 정보를 불러왔습니다 : ${response.body}")
                }
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }
}