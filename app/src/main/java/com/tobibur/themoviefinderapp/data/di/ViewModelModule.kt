package com.tobibur.themoviefinderapp.data.di

import com.tobibur.themoviefinderapp.ui.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MoviesViewModel(get()) }
}