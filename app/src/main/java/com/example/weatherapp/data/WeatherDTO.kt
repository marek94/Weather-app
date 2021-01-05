package com.example.weatherapp.data

import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("main")
    val weather: Weather
)