package com.example.androidproficiencyexercise.model

import io.reactivex.Single
import retrofit2.http.GET

interface CanadaApi {

    @GET("facts.js")
    fun getCanada(): Single<Canada>
}