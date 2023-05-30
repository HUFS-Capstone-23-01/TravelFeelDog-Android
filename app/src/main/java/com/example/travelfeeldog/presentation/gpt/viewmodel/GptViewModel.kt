package com.example.travelfeeldog.presentation.gpt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelfeeldog.data.repository.gpt.GptSearchRepository
import com.example.travelfeeldog.presentation.gpt.adapter.ItemGptSearch
import com.example.travelfeeldog.util.Event
import com.example.travelfeeldog.util.UserInfo
import kotlinx.coroutines.launch
import timber.log.Timber

class GptViewModel(private val repository: GptSearchRepository): ViewModel() {

    private val _isRequested: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()
    val isRequested: LiveData<Event<Boolean>>
        get() = _isRequested

    private val _gptHistory: MutableLiveData<Event<List<ItemGptSearch>>> = MutableLiveData<Event<List<ItemGptSearch>>>()
    val gptHistory: LiveData<Event<List<ItemGptSearch>>>
        get() = _gptHistory

    fun getGptSearchResult(userInput: String) {
        viewModelScope.launch {
            try {
                _isRequested.value = Event(true)
                val response = repository.getGptSearchResult(UserInfo.getUserInfo()!!.token, userInput)
                Timber.d("GPT응답을 불러왔습니다 : ${response.body}")
                if(response.header.status == 200) {
                    UserInfo.updateResponseHistory(UserInfo.getGptHistoryList().size-1, response.body)
                    _gptHistory.value = Event(UserInfo.getGptHistoryList())
                }
            } catch (e: Throwable) {
                Timber.d(e)
            }
        }
    }
}