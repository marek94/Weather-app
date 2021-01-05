package com.example.weatherapp.di

import com.example.weatherapp.service.ApiKeyInterceptor
import com.example.weatherapp.service.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val API_KEY = "API_KEY"
const val API_URL = "https://api.openweathermap.org/data/2.5/"

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit) = retrofit.create(WeatherService::class.java)

    @Provides
    @Singleton
    fun provideOkhttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): ApiKeyInterceptor = ApiKeyInterceptor(API_KEY)
}