package com.weather.dmacedo.weathersnowcheck.app.presentation.main

import com.weather.dmacedo.weathersnowcheck.app.datasource.response.current.CurrentWeatherResponse

interface MainContract {

    interface View {
        fun requestPermissions(permissionsNeeded: ArrayList<String>)
        fun showNoConnectionMessage()
        fun showCantAskPermissionMessage()
        fun showWeather(weather: CurrentWeatherResponse?)
        fun showError(message: String?)
    }
}
