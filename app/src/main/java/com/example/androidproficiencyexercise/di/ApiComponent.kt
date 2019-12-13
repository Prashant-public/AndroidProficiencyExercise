package com.example.androidproficiencyexercise.di

import com.example.androidproficiencyexercise.model.CanadaService
import com.example.androidproficiencyexercise.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CanadaService)

    fun inject(viewModel: ListViewModel)
}