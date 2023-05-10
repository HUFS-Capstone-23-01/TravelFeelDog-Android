package com.example.travelfeeldog.data.repository.mypage.datasource

import com.example.travelfeeldog.data.model.mypage.MyReviewResponse

interface MyPageDataSource {

    suspend fun getMyReviewList(authToken: String) : MyReviewResponse

}