package com.example.travelfeeldog.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelfeeldog.data.model.place.MostReviewPlace
import com.example.travelfeeldog.data.model.place.PopularPlace
import com.example.travelfeeldog.data.repository.place.PlaceRepository
import com.example.travelfeeldog.util.Event
import com.example.travelfeeldog.util.UserInfo
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(private val repository: PlaceRepository): ViewModel() {

    private val _popularPlace: MutableLiveData<Event<List<PopularPlace>>> = MutableLiveData<Event<List<PopularPlace>>>()
    val popularPlace: LiveData<Event<List<PopularPlace>>>
        get() = _popularPlace

    private val _mostReviewPlace: MutableLiveData<Event<List<MostReviewPlace>>> = MutableLiveData<Event<List<MostReviewPlace>>>()
    val mostReviewPlace: LiveData<Event<List<MostReviewPlace>>>
        get() = _mostReviewPlace

    fun getPopularPlace(categoryName: String, locationName: String) {
        viewModelScope.launch {
            try {
                val response = repository.getPopularPlace(UserInfo.getUserInfo()!!.token, categoryName, locationName)
                if(response.header.status == 200) {
                    _popularPlace.value = Event(response.body)
                } else{
                    Timber.d("인기 장소를 불러오는 데 실패했습니다.")
                }
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }

    fun getMostReviewPlace(locationName: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMostReviewPlace(UserInfo.getUserInfo()!!.token, locationName)
                if(response.header.status == 200) {
                    _mostReviewPlace.value = Event(response.body)
                } else {
                    Timber.d("리뷰가 많은 장소를 불러오는 데 실패했습니다.")
                }
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }




}