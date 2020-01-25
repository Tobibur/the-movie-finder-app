package com.tobibur.themoviefinderapp.data.di

import com.tobibur.themoviefinderapp.data.api.ApiConstants
import com.tobibur.themoviefinderapp.data.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        OkHttpClient.Builder().apply {
            addInterceptor(get<HttpLoggingInterceptor>())
            addInterceptor { chain ->
                var request = chain.request()
                val url = request
                    .url
                    .newBuilder()
                    .addQueryParameter("api_key", ApiConstants.API_KEY)
                    .build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }
        }.build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(ApiService::class.java) }
}