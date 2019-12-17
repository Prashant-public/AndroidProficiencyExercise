package com.example.androidproficiencyexercise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproficiencyexercise.di.DaggerApiComponent
import com.example.androidproficiencyexercise.model.Canada
import com.example.androidproficiencyexercise.model.CanadaService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel : ViewModel() {

    @Inject
    lateinit var canadaService: CanadaService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val canada = MutableLiveData<Canada>()
    val canadaLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    // Encapsulate access to mutable LiveData through getter
    fun getCanada(): LiveData<Canada> = canada
    fun getCanadaLoadError(): LiveData<Boolean> = canadaLoadError
    fun getLoading(): LiveData<Boolean> = loading

    fun refresh() {
        fetchCanadaData()
    }

    private fun fetchCanadaData() {
        loading.value = true

        disposable.add(
            canadaService.getCanadaData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Canada>() {
                    override fun onSuccess(value: Canada?) {
                        canada.value = value
                        canadaLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        canadaLoadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}