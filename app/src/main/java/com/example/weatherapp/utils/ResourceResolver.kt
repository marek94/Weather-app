package com.example.weatherapp.utils

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceResolver @Inject constructor(@ApplicationContext private val context: Context) {
    fun resolveString(@StringRes stringId: Int, vararg args: String) = context.getString(stringId, *args)
}