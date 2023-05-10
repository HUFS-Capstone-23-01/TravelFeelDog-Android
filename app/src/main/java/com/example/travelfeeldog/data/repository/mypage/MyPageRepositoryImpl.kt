package com.example.travelfeeldog.data.repository.mypage

import com.example.travelfeeldog.data.model.mypage.MyReviewResponse
import com.example.travelfeeldog.data.repository.mypage.datasource.MyPageDataSource

class MyPageRepositoryImpl(private val myPageDataSource: MyPageDataSource): MyPageRepository {

    override suspend fun getMyReviewList(authToken: String): MyReviewResponse {
        return myPageDataSource.getMyReviewList(authToken)
    }
}