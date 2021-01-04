package com.example.weatherapp.ui

import androidx.databinding.Bindable
import com.example.weatherapp.BR

class WeatherDetailsViewModel : ObservableViewModel() {
    @get:Bindable
    var city: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.city)
        }
}