package com.weather.dmacedo.weathersnowcheck.app.presentation.main

import android.annotation.TargetApi
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.weather.dmacedo.weathersnowcheck.R
import com.weather.dmacedo.weathersnowcheck.app.injection.component.activity.DaggerMainActivityComponent
import com.weather.dmacedo.weathersnowcheck.app.internal.WeatherApplication
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainPresenter

    private val PERMISSIONS_RESULT: Int = 103

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doInjections()
        presenter.loadUserLocation()
    }

    private fun doInjections() {
        DaggerMainActivityComponent.builder()
                .appComponent((application as WeatherApplication).appComponent)
                .view(this)
                .build()
                .inject(this)
    }

    override fun showLocation(location: Location?) {
        main_gps_text.text = "GPS: ${location.toString()}"
    }

    override fun requestPermissions(permissionsNeeded: ArrayList<String>) {
        if (permissionsNeeded.isEmpty()) {
            presenter.getUpdatedLocation()
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissionsNeeded.toTypedArray(), PERMISSIONS_RESULT)
        }
    }

    override fun showNoConnectionMessage() {
        AlertDialog.Builder(this)
                .setTitle("GPS is not Enabled!")
                .setMessage("Do you want to turn on GPS?")
                .setPositiveButton("Yes", { dialog, which ->
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(intent)
                })
                .setNegativeButton("No", { dialog, which ->
                    finish()
                })
                .create()
                .show()
    }

    override fun showCantAskPermissionMessage() {
        AlertDialog.Builder(this)
                .setMessage("Error enabling permissions. Try to enable location manually!")
                .setNeutralButton("Ok", { _, _ ->
                    finish()
                })
                .create()
                .show()
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_RESULT) {
            if (grantResults.contains(-1)) {
                showCantAskPermissionMessage()
            } else {
                presenter.getUpdatedLocation()
            }
        }
    }

}
