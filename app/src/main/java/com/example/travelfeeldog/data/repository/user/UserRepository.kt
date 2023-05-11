package com.example.travelfeeldog.data.repository.user

import com.example.travelfeeldog.data.model.signin.SignInResponse

interface UserRepository {

    suspend fun getUserInfoByNickname(nickname: String): SignInResponse
}