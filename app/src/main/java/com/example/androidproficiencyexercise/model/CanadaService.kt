package com.example.androidproficiencyexercise.model

import com.example.androidproficiencyexercise.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CanadaService {

    @Inject
    lateinit var api: CanadaApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCanadaData(): Single<Canada> {
        return api.getCanada()
    }
}