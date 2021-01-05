package com.example.weatherapp.service

import okhttp3.Interceptor
import okhttp3.Response

const val API_PARAMETER = "appid"

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val extendedUrl = request.url().newBuilder()
            .addQueryParameter(API_PARAMETER, apiKey)
            .build()

        val requestBuilder = request.newBuilder().url(extendedUrl)
        val requestWithApiKeyInjected = requestBuilder.build()
        return chain.proceed(requestWithApiKeyInjected)
    }
}