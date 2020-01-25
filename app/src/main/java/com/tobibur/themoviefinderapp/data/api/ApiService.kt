package com.tobibur.themoviefinderapp.data.api

import com.tobibur.themoviefinderapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/movie/popular")
    suspend fun getPopularMovies(@Query("page")page: Int): MovieResponse
}