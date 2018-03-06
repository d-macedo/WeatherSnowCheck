package com.weather.dmacedo.weathersnowcheck.app.datasource.repository

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import io.reactivex.Observable
import javax.inject.Inject

class GpsRepository @Inject constructor(private val appContext: Context) {

    companion object {
        val CODE_NO_CONNECTION = "100"
        val CODE_CANT_ASK_PERMISSION = "101"
    }

    fun requestUserPermissions(): Observable<ArrayList<String>> {
        val locationManager = appContext.getSystemService(Service.LOCATION_SERVICE) as LocationManager

        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!hasGps && !hasNetwork) {
            return Observable.error(RuntimeException(CODE_NO_CONNECTION))
        } else {
            return getNeededPermissions()
        }
    }

    private fun getNeededPermissions(): Observable<ArrayList<String>> {
        // can ask permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionsNeeded = ArrayList<String>()

            if (appContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            if (appContext.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
            return Observable.just(permissionsNeeded)

        } else {
            return Observable.error(RuntimeException(CODE_CANT_ASK_PERMISSION))
        }
    }
}