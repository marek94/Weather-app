package com.example.weatherapp.ui

import androidx.databinding.Bindable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.BR
import com.example.weatherapp.R
import com.example.weatherapp.data.WeatherInCity
import com.example.weatherapp.service.WeatherService
import com.example.weatherapp.utils.ResourceResolver
import com.example.weatherapp.utils.ResultWrapper
import com.example.weatherapp.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityInputViewModel @ViewModelInject constructor(
    private val resourceResolver: ResourceResolver,
    private val weatherService: WeatherService
) : ObservableViewModel() {
    @get:Bindable
    var city: String = ""

    @get:Bindable
    val cityError
        get() = if (cityNotSet && city.isBlank()) resourceResolver.resolveString(R.string.city_not_filled) else null

    private var cityNotSet: Boolean = false

    val weatherData = MutableLiveData<WeatherInCity>()

    val errors = MutableLiveData<String>()

    fun getWeatherInCity(city: String) = viewModelScope.launch(Dispatchers.IO) {
        val response = safeApiCall { weatherService.getWeather(city) }
        when (response) {
            is ResultWrapper.NetworkError -> errors.postValue(resourceResolver.resolveString(R.string.network_error_occurred))
            is ResultWrapper.GenericError -> errors.postValue(response.error ?: resourceResolver.resolveString(R.string.error_occurred))
            is ResultWrapper.Success -> {
                weatherData.postValue(WeatherInCity(city, response.value.weather))
            }
        }
    }

    fun validateInput(): Boolean {
        val inputIsValid = city.isNotBlank()
        if (!inputIsValid) {
            cityNotSet = true
            notifyPropertyChanged(BR.cityError)
        }
        return inputIsValid
    }
}