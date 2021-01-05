package com.example.weatherapp.ui

import com.example.weatherapp.data.WeatherInCity
import com.example.weatherapp.utils.addCelsiusSign
import com.example.weatherapp.utils.roundToFirstDecimalPlaceAsString

class WeatherDetailsViewModel : ObservableViewModel() {
    lateinit var weatherData: WeatherInCity

    val city get() = weatherData.city

    val temperature get() = addCelsiusSign(roundToFirstDecimalPlaceAsString(weatherData.weather.temp))

    val temperatureMin get() = addCelsiusSign(roundToFirstDecimalPlaceAsString(weatherData.weather.temp_min))

    val temperatureMax get() = addCelsiusSign(roundToFirstDecimalPlaceAsString(weatherData.weather.temp_max))

    val humidity get() = "${weatherData.weather.humidity} %"

    val pressure get() = "${weatherData.weather.pressure} hPa"
}