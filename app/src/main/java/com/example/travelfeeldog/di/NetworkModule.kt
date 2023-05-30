package com.example.travelfeeldog.di

import com.example.travelfeeldog.BuildConfig.BASE_URL
import com.example.travelfeeldog.data.network.NetworkInterceptor
import com.example.travelfeeldog.data.network.api.*
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .addNetworkInterceptor(NetworkInterceptor())
            .addInterceptor(Interceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder().build()
                )
            })
            .build()
    }

    single<Retrofit> {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .client(get())
                .baseUrl(BASE_URL)
                .build()
    }

    single<TestApi> {
        get<Retrofit>().create(TestApi::class.java)
    }

    single<AuthApi> {
        get<Retrofit>().create(AuthApi::class.java)
    }

    single<MyPageApi> {
        get<Retrofit>().create(MyPageApi::class.java)
    }

    single<PlaceApi> {
        get<Retrofit>().create(PlaceApi::class.java)
    }

    single<ReviewApi> {
        get<Retrofit>().create(ReviewApi::class.java)
    }

    single<GptApi> {
        get<Retrofit>().create(GptApi::class.java)
    }
}