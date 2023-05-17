package com.example.travelfeeldog.presentation.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelfeeldog.data.model.search.SearchingPlaceInfo
import com.example.travelfeeldog.data.repository.place.PlaceRepository
import com.example.travelfeeldog.util.Constants
import com.example.travelfeeldog.util.Event
import com.example.travelfeeldog.util.UserInfo.getUserInfo
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel(private val repository: PlaceRepository) : ViewModel() {

    private val _userInput: MutableLiveData<String> = MutableLiveData<String>("")
    val userInput: LiveData<String>
        get() = _userInput

    private val _categoryName: MutableLiveData<String> = MutableLiveData<String>(Constants.defaultCategory)
    val categoryName: LiveData<String>
        get() = _categoryName

    private val _locationName: MutableLiveData<String> = MutableLiveData<String>(Constants.defaultLocation)
    val locationName: LiveData<String>
        get() = _locationName

    private val _searchResult: MutableLiveData<Event<List<SearchingPlaceInfo>>> = MutableLiveData<Event<List<SearchingPlaceInfo>>>()
    val searchResult: LiveData<Event<List<SearchingPlaceInfo>>>
        get() = _searchResult

    private val _isValidOptionSet: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()
    val isValidOptionSet: LiveData<Event<Boolean>>
        get() = _isValidOptionSet


    fun getSearchResult() {
        viewModelScope.launch {
            Timber.d("검색어: ${_userInput.value} 지역: ${_locationName.value} 카테고리: ${_categoryName.value}")
            try {
                val response = repository.getSearchResult(
                    getUserInfo()!!.token,
                    _userInput.value!!,
                    _categoryName.value!!,
                    locationName.value!!
                )
                if(response.header.status == 200) {
                    _searchResult.value = Event(response.body)
                } else {
                    Timber.d("검색 결과를 불러오는 데 실패했습니다.")
                }
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }

    fun setUserInput(text: String) {
        _userInput.value = text
        _isValidOptionSet.value = Event(true)
    }
    fun setCategoryName(category: String) {
        _categoryName.value = category
        _isValidOptionSet.value = Event(true)
    }
    fun setLocation(location: String) {
        _locationName.value = location
        _isValidOptionSet.value = Event(true)
    }

    fun initOption() {
        _userInput.value = ""
        _categoryName.value = Constants.defaultCategory
        _locationName.value = Constants.defaultLocation
        _isValidOptionSet.value = Event(true)
        Timber.d("초기화 함 : ${_categoryName.value}")
    }

    fun isSame(category: String): Boolean {
        return _categoryName.value == category
    }

}