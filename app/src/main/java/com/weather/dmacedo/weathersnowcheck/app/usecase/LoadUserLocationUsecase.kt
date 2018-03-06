package com.weather.dmacedo.weathersnowcheck.app.usecase

import com.weather.dmacedo.weathersnowcheck.app.datasource.repository.GpsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoadUserLocationUsecase @Inject constructor(private val gpsRepository: GpsRepository) {

    fun requestPermissions(): Observable<ArrayList<String>> {
        return gpsRepository.requestUserPermissions()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

}