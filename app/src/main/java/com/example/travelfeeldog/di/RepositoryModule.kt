package com.example.travelfeeldog.di

import com.example.travelfeeldog.data.repository.mypage.MyPageRepository
import com.example.travelfeeldog.data.repository.mypage.MyPageRepositoryImpl
import com.example.travelfeeldog.data.repository.place.PlaceRepository
import com.example.travelfeeldog.data.repository.place.PlaceRepositoryImpl
import com.example.travelfeeldog.data.repository.review.PostReviewRepository
import com.example.travelfeeldog.data.repository.review.PostReviewRepositoryImpl
import com.example.travelfeeldog.data.repository.sign.AuthRepository
import com.example.travelfeeldog.data.repository.sign.AuthRepositoryImpl
import com.example.travelfeeldog.data.repository.user.UserRepository
import com.example.travelfeeldog.data.repository.user.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<MyPageRepository> { MyPageRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<PlaceRepository> { PlaceRepositoryImpl(get()) }
    single<PostReviewRepository> { PostReviewRepositoryImpl(get()) }
}