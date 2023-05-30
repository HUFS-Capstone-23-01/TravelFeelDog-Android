package com.example.travelfeeldog.data.repository.gpt

import com.example.travelfeeldog.data.model.gpt.GptSearchResultResponse

interface GptSearchRepository {

    suspend fun getGptSearchResult(userToken: String, userInput: String): GptSearchResultResponse
}