package com.example.weatherapp.service

import com.example.weatherapp.data.WeatherDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getWeather(@Query("q") city: String, @Query("units") unit: String = "metric"): WeatherDTO
}