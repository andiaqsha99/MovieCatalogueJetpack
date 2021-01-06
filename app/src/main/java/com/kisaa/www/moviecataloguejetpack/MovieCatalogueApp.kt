package com.kisaa.www.moviecataloguejetpack

import android.app.Application
import com.kisaa.www.moviecataloguejetpack.core.di.databaseModule
import com.kisaa.www.moviecataloguejetpack.core.di.networkModule
import com.kisaa.www.moviecataloguejetpack.core.di.repositoryModule
import com.kisaa.www.moviecataloguejetpack.di.useCaseModule
import com.kisaa.www.moviecataloguejetpack.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieCatalogueApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MovieCatalogueApp)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    useCaseModule
                )
            )
        }
    }
}