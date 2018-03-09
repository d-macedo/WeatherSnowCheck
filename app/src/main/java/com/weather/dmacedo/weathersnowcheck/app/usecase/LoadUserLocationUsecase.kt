package com.weather.dmacedo.weathersnowcheck.app.usecase

import android.location.Location
import com.weather.dmacedo.weathersnowcheck.app.datasource.repository.GpsRepository
import io.reactivex.Observable
import javax.inject.Inject

class LoadUserLocationUsecase @Inject constructor(private val gpsRepository: GpsRepository) {

    fun requestPermissions(): Observable<ArrayList<String>> {
        return gpsRepository.requestUserPermissions()
    }


    fun getLocation(): Observable<Location> {
        return gpsRepository.getUserLocation()
    }
}