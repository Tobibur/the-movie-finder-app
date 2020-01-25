package com.tobibur.themoviefinderapp.data.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            var request = chain.request()
            val url = request
                .url()
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
        .build()

    private const val API_KEY = "a041f33b5c637d750bc3ef8814412bb0"
    const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
}