package com.example.androidproficiencyexercise.di

import com.example.androidproficiencyexercise.model.CanadaApi
import com.example.androidproficiencyexercise.model.CanadaService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

    @Provides
    fun provideArticlesApi(): CanadaApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CanadaApi::class.java)
    }

    @Provides
    fun provideArticlesService(): CanadaService {
        return CanadaService()
    }
}