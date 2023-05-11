package com.example.travelfeeldog.data.repository.user.datasource

import com.example.travelfeeldog.data.model.signin.SignInResponse

interface UserDataSource {

    suspend fun getUserInfoByNickname(nickname: String): SignInResponse
}