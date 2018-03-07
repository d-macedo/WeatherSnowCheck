package com.weather.dmacedo.weathersnowcheck.app.presentation.main

import android.location.Location

interface MainContract {

    interface View {
        fun requestPermissions(permissionsNeeded: ArrayList<String>)
        fun showNoConnectionMessage()
        fun showCantAskPermissionMessage()
        fun showLocation(location: Location?)
    }
}
