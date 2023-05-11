package com.example.travelfeeldog.presentation.place.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelfeeldog.data.model.place.PlaceInfo
import com.example.travelfeeldog.data.repository.place.PlaceRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class PlaceViewModel(private val repository: PlaceRepository) : ViewModel() {

    private val _placeInfo: MutableLiveData<PlaceInfo> = MutableLiveData<PlaceInfo>()
    val placeInfo: LiveData<PlaceInfo>
        get() = _placeInfo

    fun getPlaceInfo(authToken: String, placeId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getPlaceInfo(authToken, placeId)
                if (response.header.status == 200) {
                    _placeInfo.value = response.body
                    Timber.d("해당 장소에 대한 정보를 불러왔습니다 : ${response.body}")
                } else {
                    Timber.d("해당 장소에 대한 정보를 불러오는 데 실패했습니다 : status(${response.header.status})")
                }
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }


}