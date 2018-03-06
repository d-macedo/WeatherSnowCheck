package com.weather.dmacedo.weathersnowcheck.app.presentation.main

import android.util.Log
import com.weather.dmacedo.weathersnowcheck.app.usecase.LoadUserLocationUsecase
import javax.inject.Inject

class MainPresenter @Inject constructor(private val view: MainContract.View,
                                        private val loadUserLocationUsecase: LoadUserLocationUsecase) {


    fun loadUserLocation() {
        loadUserLocationUsecase.requestPermissions()
                .subscribe({ permissionsNeeded ->
                    view.requestPermissions(permissionsNeeded)
                }, { error ->
                    Log.e("ERROR", error.message)
                })

    }
}
