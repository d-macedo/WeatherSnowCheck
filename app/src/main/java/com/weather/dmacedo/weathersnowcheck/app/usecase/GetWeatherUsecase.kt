package com.weather.dmacedo.weathersnowcheck.app.usecase

import com.weather.dmacedo.weathersnowcheck.app.datasource.repository.WeatherRepository
import com.weather.dmacedo.weathersnowcheck.app.datasource.response.current.CurrentWeatherResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetWeatherUsecase @Inject constructor(private val weatherRepository: WeatherRepository) {

    fun execute(lat: Double, lng: Double): Observable<CurrentWeatherResponse> {
        return weatherRepository.getCurrentWeather(lat.toString(), lng.toString())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
