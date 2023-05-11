package com.example.travelfeeldog.data.repository.user

import com.example.travelfeeldog.data.model.signin.SignInResponse
import com.example.travelfeeldog.data.repository.user.datasource.UserDataSource

class UserRepositoryImpl(private val userDataSource: UserDataSource): UserRepository {

    override suspend fun getUserInfoByNickname(nickname: String): SignInResponse {
        return userDataSource.getUserInfoByNickname(nickname)
    }
}