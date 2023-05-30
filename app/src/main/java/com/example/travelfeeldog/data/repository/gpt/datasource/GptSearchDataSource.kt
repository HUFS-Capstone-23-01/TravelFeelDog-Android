package com.example.travelfeeldog.data.repository.gpt.datasource

import com.example.travelfeeldog.data.model.gpt.GptSearchResultResponse

interface GptSearchDataSource {

    suspend fun getGptSearchResult(userToken: String, userInput: String): GptSearchResultResponse
}