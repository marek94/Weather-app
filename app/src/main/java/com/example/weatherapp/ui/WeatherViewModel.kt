package com.example.weatherapp.ui

import androidx.databinding.Bindable
import androidx.hilt.lifecycle.ViewModelInject
import com.example.weatherapp.BR
import com.example.weatherapp.R
import com.example.weatherapp.utils.ResourceResolver


class WeatherViewModel @ViewModelInject constructor(private val resourceResolver: ResourceResolver) : ObservableViewModel() {
    @get:Bindable
    var city: String = ""

    @get:Bindable
    val cityError get() = if (cityNotSet && city.isBlank()) resourceResolver.resolveString(R.string.city_not_filled) else null

    private var cityNotSet: Boolean = false

    fun validateInput(): Boolean {
        val inputIsValid = city.isNotBlank()
        if (!inputIsValid) {
            cityNotSet = true
            notifyPropertyChanged(BR.cityError)
        }
        return inputIsValid
    }
}