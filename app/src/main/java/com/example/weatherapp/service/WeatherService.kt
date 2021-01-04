package com.example.weatherapp.service

import com.example.weatherapp.data.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherService {
    @GET
    suspend fun getWeather(@QueryMap map: Map<String, String>): Response<WeatherData>
}