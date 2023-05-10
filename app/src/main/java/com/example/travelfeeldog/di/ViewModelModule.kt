package com.example.travelfeeldog.di

import com.example.travelfeeldog.presentation.mypage.viewmodel.MyPageViewModel
import com.example.travelfeeldog.presentation.signin.viewmodel.AuthViewModel
import com.example.travelfeeldog.util.TestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { TestViewModel(get()) }
    viewModel { AuthViewModel(get()) }
    viewModel { MyPageViewModel(get()) }
}