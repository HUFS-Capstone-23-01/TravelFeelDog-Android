package com.example.travelfeeldog.data.repository.gpt.datasource

import com.example.travelfeeldog.data.model.gpt.GptSearchResultResponse
import com.example.travelfeeldog.data.network.api.GptApi

class GptSearchDataSourceImpl(private val gptApi: GptApi ): GptSearchDataSource {

    override suspend fun getGptSearchResult(userToken: String, userInput: String): GptSearchResultResponse {
        return gptApi.getGptSearchResult(userToken, userInput)
    }
}