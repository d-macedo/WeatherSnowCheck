package com.weather.dmacedo.weathersnowcheck.app.datasource.remote

import com.weather.dmacedo.weathersnowcheck.app.datasource.response.current.CurrentWeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET ("weather")
    fun currentWeather(@Query("lat") lat: String,
                       @Query("lon") lon: String,
                       @Query("units") units: String,
                       @Query("APPID") appId: String): Observable<CurrentWeatherResponse>

}