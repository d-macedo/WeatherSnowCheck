package com.weather.dmacedo.weathersnowcheck.app.datasource.repository

import android.content.Context
import com.weather.dmacedo.weathersnowcheck.R
import com.weather.dmacedo.weathersnowcheck.app.datasource.remote.WeatherApi
import com.weather.dmacedo.weathersnowcheck.app.datasource.response.current.CurrentWeatherResponse
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val webService: WeatherApi,
                                            private val context: Context) {

    fun getCurrentWeather(lat: String, lon: String): Observable<CurrentWeatherResponse> {
        val apiKey: String = context.assets
                .open(context.getString(R.string.file_name_api_key))
                .bufferedReader()
                .use { it.readText() }
        return webService.currentWeather(lat, lon, "metric", apiKey)
    }
}