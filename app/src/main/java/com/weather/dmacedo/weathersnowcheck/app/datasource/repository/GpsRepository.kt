package com.weather.dmacedo.weathersnowcheck.app.datasource.repository

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.weather.dmacedo.weathersnowcheck.app.internal.CustomException
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import javax.inject.Inject

class GpsRepository @Inject constructor(private val appContext: Context) {

    companion object {
        val CODE_NO_CONNECTION = 100
        val CODE_CANT_ASK_PERMISSION = 101
    }

    private var locationManager = appContext.getSystemService(Service.LOCATION_SERVICE) as LocationManager
    private var hasGps: Boolean = false
    private var hasNetwork: Boolean = false

    fun requestUserPermissions(): Observable<ArrayList<String>> {
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!hasGps && !hasNetwork) {
            return Observable.error(CustomException.emit(CODE_NO_CONNECTION))
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
            return Observable.error(CustomException.emit(CODE_CANT_ASK_PERMISSION))
        }
    }

    fun getUserLocation(): Observable<Location> {
        return Observable.create({ emitter: ObservableEmitter<Location> ->
            try {
                if (hasGps) {
                    locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER,
                            object : LocationReveicedListener() {
                                override fun onLocationReceived(location: Location) {
                                    Log.wtf("TESTE", location.toString())
                                    emitter.onNext(location)
                                    emitter.onComplete()
                                }
                            },
                            null)
                } else if (hasNetwork) {
                    locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER,
                            object : LocationReveicedListener() {
                                override fun onLocationReceived(location: Location) {
                                    Log.wtf("TESTE", location.toString())
                                    emitter.onNext(location)
                                    emitter.onComplete()
                                }
                            },
                            null)
                }
            } catch (exception: SecurityException) {
                Log.wtf("TESTE", exception.toString())
                emitter.onError(exception)
            }
        })
    }


    abstract class LocationReveicedListener : LocationListener {

        abstract fun onLocationReceived(location: Location)

        override fun onLocationChanged(location: Location) {
            Log.d("TESTE_OK", location.toString())
            onLocationReceived(location)
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            Log.wtf("TESTE", "onStatusChanged")
        }

        override fun onProviderEnabled(p0: String?) {
            Log.wtf("TESTE", "onProviderEnabled")
        }

        override fun onProviderDisabled(p0: String?) {
            Log.wtf("TESTE", "onProviderDisabled")
        }
    }
}