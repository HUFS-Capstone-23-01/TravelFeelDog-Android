package com.example.travelfeeldog.presentation.place.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelfeeldog.data.model.place.PlaceInfo
import com.example.travelfeeldog.data.model.review.PlaceReview
import com.example.travelfeeldog.data.repository.place.PlaceRepository
import com.example.travelfeeldog.util.Event
import kotlinx.coroutines.launch
import timber.log.Timber

class PlaceViewModel(private val repository: PlaceRepository) : ViewModel() {

    private val _placeId: MutableLiveData<Int> = MutableLiveData<Int>()
    val placeId: LiveData<Int>
        get() = _placeId

    private val _placeCategoryId: MutableLiveData<Int> = MutableLiveData<Int>()
    val placeCategoryId: LiveData<Int>
        get() = _placeCategoryId

    private val _placeInfo: MutableLiveData<PlaceInfo> = MutableLiveData<PlaceInfo>()
    val placeInfo: LiveData<PlaceInfo>
        get() = _placeInfo

    private val _placeReview: MutableLiveData<Event<List<PlaceReview>>> = MutableLiveData<Event<List<PlaceReview>>>()
    val placeReview: LiveData<Event<List<PlaceReview>>>
        get() = _placeReview


    // -------------------- 서버 통신 --------------------

    fun getPlaceInfo(authToken: String) {
        viewModelScope.launch {
            try {
                val response = repository.getPlaceInfo(authToken, _placeId.value!!)
                if (response.header.status == 200) {
                    _placeInfo.value = response.body
                    _placeId.value = response.body.id
                    _placeCategoryId.value = response.body.categoryId
                    Timber.d("해당 장소에 대한 정보를 불러왔습니다 : ${placeInfo.value}")
                } else {
                    Timber.d("해당 장소에 대한 정보를 불러오는 데 실패했습니다 : status(${response.header.status})")
                }
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }

    fun getPlaceReview(authToken: String) {
        viewModelScope.launch {
            try {
                val response = repository.getPlaceReview(authToken, _placeId.value!!)
                if(response.header.status == 200) {
                    _placeReview.value = Event(response.body)
                } else {
                    Timber.d("해당 장소에 대한 정보를 불러오는 데 실패했습니다 : status(${response.header.status})")
                }
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }

    // -------------------- 뷰 관련 로직 요청 처리 --------------------

    fun setRequestPlace(placeId: Int) {
        _placeId.value = placeId
    }

}