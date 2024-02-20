package com.example.schoolapplication.api

import com.example.schoolapplication.BuildConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor

val NetworkModule = module {
    single{
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    single{
        Retrofit
            .Builder()
            .client(get())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<SchoolApi>{SchoolApiImpl(get())}
}