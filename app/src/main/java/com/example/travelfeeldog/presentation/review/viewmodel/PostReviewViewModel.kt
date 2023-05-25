package com.example.travelfeeldog.presentation.review.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelfeeldog.data.model.review.KeywordSet
import com.example.travelfeeldog.data.model.review.PlaceUserEvaluationResultRequest
import com.example.travelfeeldog.data.model.review.file.FileUrl
import com.example.travelfeeldog.data.repository.review.PostReviewRepository
import com.example.travelfeeldog.util.Event
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import java.io.File

class PostReviewViewModel(private val repository: PostReviewRepository): ViewModel() {

    private val _files: MutableList<MultipartBody.Part> = mutableListOf()

    private val _placeReviewKeywordSet: MutableLiveData<Event<KeywordSet>> = MutableLiveData<Event<KeywordSet>>()
    val placeReviewKeywordSet: LiveData<Event<KeywordSet>>
        get() = _placeReviewKeywordSet

    private val _reviewImageSet: MutableLiveData<Event<List<String>>> = MutableLiveData<Event<List<String>>>()
    val reviewImageSet: LiveData<Event<List<String>>>
        get() = _reviewImageSet

    private val _isPostedReview: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()
    val isPostedReview: LiveData<Event<Boolean>>
        get() = _isPostedReview

    // -------------------- 서버 통신 --------------------

    fun postReviewImage() {
        viewModelScope.launch {
            try {
                when(_files.size) {
                    0 -> {
                        _reviewImageSet.value = Event(emptyList())
                    }
                    else -> {
                        val response = repository.postReviewImage("review", _files.toList())
                        if(response.header.status == 201) {
                            _reviewImageSet.value = Event(getStringUrls(response.body))
                        }
                    }
                }
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }

    fun postReview(authToken:String, reviewSet : PlaceUserEvaluationResultRequest) {
        viewModelScope.launch {
            try {
                val response = repository.postPlaceEvaluation(authToken, reviewSet)
                _isPostedReview.value = Event(response.header.status == 200)
                Timber.d("등록한 리뷰 정보입니다. : $reviewSet")
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }

    fun getPlaceKeyword(categoryId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getPlaceKeyword(categoryId)
                if(response.header.status == 200) {
                    _placeReviewKeywordSet.value = Event(response.body)
                    Timber.d("키워드 셋을 불러왔습니다 : ${response.body}")
                } else {
                    Timber.d("키워드 셋을 불러오는 데 실패했습니다.")
                }
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }

    // -------------------- 뷰 관련 로직 요청 처리 --------------------

    fun initFileList() {
        _files.clear()
    }

    fun convertFileToFormData(file: File) {
        val requestFile = file.asRequestBody("image/*".toMediaType())
        val body = MultipartBody.Part.createFormData("files", file.name, requestFile)
        _files.add(body)
    }

    fun checkValidEvaluation() {
    }

    // -------------------- 내부 로직 처리 --------------------

    private fun getStringUrls(urls: List<FileUrl>): List<String> {
        val stringUrlList: MutableList<String> = mutableListOf()
        for(url in urls) {
            stringUrlList.add(url.fileUrls)
        }
        return stringUrlList.toList()
    }
}