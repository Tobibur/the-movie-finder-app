package com.tobibur.themoviefinderapp.data.repository

import com.tobibur.themoviefinderapp.data.api.ApiService
import com.tobibur.themoviefinderapp.data.model.MovieResponse

class PopularMoviesRepository(private val apiService : ApiService){

    suspend fun getPopularMovies(page: Int): Outcome<MovieResponse>{
        val moviesDataSource = PopularMoviesNetworkDataSource(apiService)
        moviesDataSource.fetchPopularMovies(page)
        return moviesDataSource.downloadedMovieResponse.value as Outcome<MovieResponse>
    }
}