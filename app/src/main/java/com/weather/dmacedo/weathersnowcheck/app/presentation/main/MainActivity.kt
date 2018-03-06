package com.weather.dmacedo.weathersnowcheck.app.presentation.main

import android.annotation.TargetApi
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.weather.dmacedo.weathersnowcheck.R
import com.weather.dmacedo.weathersnowcheck.app.injection.component.activity.DaggerMainActivityComponent
import com.weather.dmacedo.weathersnowcheck.app.internal.WeatherApplication
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainPresenter

    private val PERMISSIONS_RESULT: Int = 103

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doInjections()
//        getGpsData()
        presenter.loadUserLocation()
    }

    private fun getLocation(hasGps: Boolean, hasNetwork: Boolean, locationManager: LocationManager) {
        try {
            //priority
            if (hasGps) {
                locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, MyLocationListener(), null)
            } else if (hasNetwork) {
                locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, MyLocationListener(), null)
            }
        } catch (exception: SecurityException) {
            Log.e("TESTE", exception.message)
        }
    }

    private fun showNoConnectionAlert() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("GPS is not Enabled!")
        alertDialog.setMessage("Do you want to turn on GPS?")
        alertDialog.setPositiveButton("Yes", { dialog, which ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        })

        alertDialog.setNegativeButton("No", { dialog, which ->
            dialog.cancel()
            //TODO: close application
        })
        alertDialog.show()
    }


    private fun doInjections() {
        DaggerMainActivityComponent.builder()
                .appComponent((application as WeatherApplication).appComponent)
                .view(this)
                .build()
                .inject(this)
    }

    override fun requestPermissions(permissionsNeeded: ArrayList<String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissionsNeeded.toTypedArray(), PERMISSIONS_RESULT)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_RESULT) {
            if (grantResults.contains(-1)) {
                //todo: user do not accepted the required permission
            } else {
                //todo: OK, get location!
            }
        }
    }

    class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location?) {
            Log.d("TESTE_OK", location.toString())
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        }

        override fun onProviderEnabled(p0: String?) {
        }

        override fun onProviderDisabled(p0: String?) {
        }
    }
}
