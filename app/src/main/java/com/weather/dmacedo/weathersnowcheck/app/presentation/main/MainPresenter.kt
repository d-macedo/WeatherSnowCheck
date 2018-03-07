package com.weather.dmacedo.weathersnowcheck.app.presentation.main

import android.util.Log
import com.weather.dmacedo.weathersnowcheck.app.datasource.repository.GpsRepository
import com.weather.dmacedo.weathersnowcheck.app.internal.CustomException
import com.weather.dmacedo.weathersnowcheck.app.usecase.LoadUserLocationUsecase
import javax.inject.Inject

class MainPresenter @Inject constructor(private val view: MainContract.View,
                                        private val loadUserLocationUsecase: LoadUserLocationUsecase) {


    fun loadUserLocation() {
        loadUserLocationUsecase.requestPermissions()
                .subscribe({ permissionsNeeded ->
                    view.requestPermissions(permissionsNeeded)
                }, { error ->
                    if (error is CustomException) {
                        handleCustomError(error.errorCode)
                    } else {
                        Log.wtf("ERROR", error.message)
                    }
                })

    }

    private fun handleCustomError(errorCode: Int) {
        when (errorCode) {
            GpsRepository.CODE_NO_CONNECTION -> view.showNoConnectionMessage()
            GpsRepository.CODE_CANT_ASK_PERMISSION -> view.showCantAskPermissionMessage()
        }
    }

    fun getUpdatedLocation() {
        loadUserLocationUsecase.getLocation()
                .subscribe({
                    view.showLocation(it)
                }, {
                    Log.wtf("ERROR", it.message)
                })
    }
}
