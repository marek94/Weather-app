package com.example.weatherapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.WeatherInCity

class SharedViewModel : ViewModel() {
    val weatherData = MutableLiveData<WeatherInCity>()
}