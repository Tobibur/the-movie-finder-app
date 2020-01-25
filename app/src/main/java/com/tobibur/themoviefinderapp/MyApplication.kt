package com.tobibur.themoviefinderapp

import android.app.Application
import com.tobibur.themoviefinderapp.data.di.dataModule
import com.tobibur.themoviefinderapp.data.di.networkModule
import com.tobibur.themoviefinderapp.data.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application(){


    override fun onCreate() {
        super.onCreate()

        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    dataModule,
                    viewModelModule
                )
            )
        }
    }
}