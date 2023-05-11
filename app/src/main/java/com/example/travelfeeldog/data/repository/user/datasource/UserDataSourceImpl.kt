package com.example.travelfeeldog.data.repository.user.datasource

import com.example.travelfeeldog.data.model.signin.SignInResponse
import com.example.travelfeeldog.data.network.api.AuthApi

class UserDataSourceImpl(private val authApi: AuthApi): UserDataSource {

    override suspend fun getUserInfoByNickname(nickname: String): SignInResponse {
        return authApi.getUserInfoByNickname(nickname)
    }
}