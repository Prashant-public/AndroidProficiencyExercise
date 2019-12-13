package com.example.androidproficiencyexercise.model

import com.google.gson.annotations.SerializedName

data class Canada(
    @SerializedName("rows")
    val aboutCanadaArray: List<AboutCanada>,
    val title: String
)