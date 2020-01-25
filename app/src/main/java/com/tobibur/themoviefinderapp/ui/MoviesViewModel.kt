package com.tobibur.themoviefinderapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tobibur.themoviefinderapp.data.repository.PopularMoviesRepository

class MoviesViewModel(private val popularMoviesRepository: PopularMoviesRepository) : ViewModel() {

    fun popularMovies(page: Int) = liveData {
        emit(popularMoviesRepository.getPopularMovies(page))
    }

}
