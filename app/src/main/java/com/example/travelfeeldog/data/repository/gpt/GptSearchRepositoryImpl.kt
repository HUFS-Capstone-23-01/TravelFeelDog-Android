package com.example.travelfeeldog.data.repository.gpt

import com.example.travelfeeldog.data.model.gpt.GptSearchResultResponse
import com.example.travelfeeldog.data.repository.gpt.datasource.GptSearchDataSource

class GptSearchRepositoryImpl(private val gptDataSource: GptSearchDataSource): GptSearchRepository {

    override suspend fun getGptSearchResult(userToken: String, userInput: String): GptSearchResultResponse {
        return gptDataSource.getGptSearchResult(userToken, userInput)
    }
}