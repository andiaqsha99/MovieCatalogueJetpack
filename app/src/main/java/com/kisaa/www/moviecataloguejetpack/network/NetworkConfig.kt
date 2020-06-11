package com.kisaa.www.moviecataloguejetpack.network

import com.kisaa.www.moviecataloguejetpack.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConfig {

    private fun getNetwork(): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun api(): ApiService {
        return getNetwork().create(ApiService::class.java)
    }
}