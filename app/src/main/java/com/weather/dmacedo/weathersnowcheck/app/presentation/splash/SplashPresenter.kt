package com.weather.dmacedo.weathersnowcheck.app.presentation.splash

import com.weather.dmacedo.weathersnowcheck.app.datasource.repository.WeatherRepository
import javax.inject.Inject

class SplashPresenter @Inject constructor(private val view: SplashContract.View,
                                          private val currentWeatherRepository: WeatherRepository) {

}