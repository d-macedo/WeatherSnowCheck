package com.weather.dmacedo.weathersnowcheck.app.presentation.splash

import android.util.Log
import com.weather.dmacedo.weathersnowcheck.app.datasource.repository.CurrentWeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashPresenter @Inject constructor(private val view: SplashContract.View, private val currentWeatherRepository: CurrentWeatherRepository) {

    fun getCurrentWeather() {
        currentWeatherRepository.getCurrentWeather("35", "139")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ currentResponse ->
                    Log.i("DEU BOM CARAI!", currentResponse.toString())
                }, { error ->
                    Log.i("DEU RUIM PORRA!", error.message)
                })
    }

}