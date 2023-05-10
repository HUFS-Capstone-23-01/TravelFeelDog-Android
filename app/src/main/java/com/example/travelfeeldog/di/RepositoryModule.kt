package com.example.travelfeeldog.di

import com.example.travelfeeldog.data.repository.mypage.MyPageRepository
import com.example.travelfeeldog.data.repository.mypage.MyPageRepositoryImpl
import com.example.travelfeeldog.data.repository.sign.AuthRepository
import com.example.travelfeeldog.data.repository.sign.AuthRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<MyPageRepository> { MyPageRepositoryImpl(get()) }
}