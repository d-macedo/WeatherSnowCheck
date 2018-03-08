package com.weather.dmacedo.weathersnowcheck.app.presentation.main

import android.location.Location
import android.util.Log
import com.weather.dmacedo.weathersnowcheck.app.datasource.repository.GpsRepository
import com.weather.dmacedo.weathersnowcheck.app.internal.CustomException
import com.weather.dmacedo.weathersnowcheck.app.usecase.GetWeatherUsecase
import com.weather.dmacedo.weathersnowcheck.app.usecase.LoadUserLocationUsecase
import javax.inject.Inject

class MainPresenter @Inject constructor(private val view: MainContract.View,
                                        private val loadUserLocation: LoadUserLocationUsecase,
                                        private val getWeather: GetWeatherUsecase) {


    fun requestPermissions() {
        loadUserLocation.requestPermissions()
                .subscribe({ view.requestPermissions(it) },
                        { error ->
                            if (error is CustomException) {
                                handleCustomError(error.errorCode)
                            } else {
                                view.showError(error.message)
                            }
                        })

    }

    fun getUserWeather() {
        loadUserLocation.getLocation()
                .subscribe({ doWeatherRequest(it) }, { Log.wtf("ERROR", it.message) })
    }

    private fun doWeatherRequest(location: Location) {
        getWeather.execute(location.latitude, location.longitude)
                .subscribe({ weather ->
                    view.showWeather(weather)
                }, { error ->
                    view.showError(error.message)
                })
    }

    private fun handleCustomError(errorCode: Int) {
        when (errorCode) {
            GpsRepository.CODE_NO_CONNECTION -> view.showNoConnectionMessage()
            GpsRepository.CODE_CANT_ASK_PERMISSION -> view.showCantAskPermissionMessage()
        }
    }
}
