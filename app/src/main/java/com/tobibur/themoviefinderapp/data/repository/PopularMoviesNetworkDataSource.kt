package com.tobibur.themoviefinderapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tobibur.themoviefinderapp.data.api.ApiService
import com.tobibur.themoviefinderapp.data.model.MovieResponse
import java.lang.Exception

class PopularMoviesNetworkDataSource(private val apiService : ApiService){

    private val _downloadedMovieDetailsResponse =  MutableLiveData<Outcome<MovieResponse>>()
    val downloadedMovieResponse: LiveData<Outcome<MovieResponse>>
        get() = _downloadedMovieDetailsResponse

    suspend fun fetchPopularMovies(page: Int){
        try {
            val movieResponse = apiService.getPopularMovies(page)
            _downloadedMovieDetailsResponse.value = Outcome.success(movieResponse)
        }catch (e: Exception){
            _downloadedMovieDetailsResponse.value = Outcome.failure(e)
        }
    }

}