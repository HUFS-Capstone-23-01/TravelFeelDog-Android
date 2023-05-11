package com.example.travelfeeldog.data.repository.mypage.datasource

import com.example.travelfeeldog.data.model.mypage.MyReviewResponse
import com.example.travelfeeldog.data.network.api.MyPageApi

class MyPageDataSourceImpl(private val myPageApi: MyPageApi): MyPageDataSource {

    override suspend fun getMyReviewList(authToken: String): MyReviewResponse {
        return myPageApi.getMyReviewList(authToken)
    }
}