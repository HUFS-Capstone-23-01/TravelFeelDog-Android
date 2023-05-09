package com.example.travelfeeldog.di

import com.example.travelfeeldog.data.repository.sign.AuthRepository
import com.example.travelfeeldog.data.repository.sign.AuthRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}