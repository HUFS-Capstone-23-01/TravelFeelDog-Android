package com.example.travelfeeldog.di

import com.example.travelfeeldog.data.repository.mypage.datasource.MyPageDataSource
import com.example.travelfeeldog.data.repository.mypage.datasource.MyPageDataSourceImpl
import com.example.travelfeeldog.data.repository.sign.datasource.AuthDataSource
import com.example.travelfeeldog.data.repository.sign.datasource.AuthDataSourceImpl
import com.example.travelfeeldog.data.repository.user.datasource.UserDataSource
import com.example.travelfeeldog.data.repository.user.datasource.UserDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<AuthDataSource> { AuthDataSourceImpl(get()) }
    single<MyPageDataSource> { MyPageDataSourceImpl(get()) }
    single<UserDataSource> { UserDataSourceImpl(get()) }
}