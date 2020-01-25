package com.tobibur.themoviefinderapp.data.di

import com.tobibur.themoviefinderapp.data.repository.PopularMoviesRepository
import org.koin.dsl.module

val dataModule = module {

    single { PopularMoviesRepository(get()) }
}