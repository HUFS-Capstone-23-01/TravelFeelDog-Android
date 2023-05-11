package com.example.travelfeeldog.data.repository.mypage

import com.example.travelfeeldog.data.model.mypage.MyReviewResponse

interface MyPageRepository {

    suspend fun getMyReviewList(authToken: String): MyReviewResponse
}