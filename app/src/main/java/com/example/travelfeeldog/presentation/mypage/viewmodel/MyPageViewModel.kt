package com.example.travelfeeldog.presentation.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelfeeldog.data.model.mypage.MyReviewList
import com.example.travelfeeldog.data.repository.mypage.MyPageRepository
import com.example.travelfeeldog.data.repository.sign.AuthRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MyPageViewModel(
    private val myPageRepository: MyPageRepository) : ViewModel() {

    private val _myReviewList: MutableLiveData<List<MyReviewList>> =
        MutableLiveData<List<MyReviewList>>()
    val myReviewList: LiveData<List<MyReviewList>> = _myReviewList

    fun getMyReviewList(authToken: String) {
        viewModelScope.launch {
            try {
                val response = myPageRepository.getMyReviewList(authToken)
                if (response.header.status == 200) {
                    _myReviewList.value = response.body
                    Timber.d("사용자가 작성한 리뷰 리스트를 불러왔습니다 : ${_myReviewList.value}")
                } else {
                    Timber.d("사용자가 작성한 리뷰를 불러오는 데 오류가 발생했습니다.")
                }
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }

}