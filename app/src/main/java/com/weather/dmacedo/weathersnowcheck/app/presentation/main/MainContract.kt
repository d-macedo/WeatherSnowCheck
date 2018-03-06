package com.weather.dmacedo.weathersnowcheck.app.presentation.main

interface MainContract {

    interface View {
        fun requestPermissions(permissionsNeeded: ArrayList<String>)
    }
}
