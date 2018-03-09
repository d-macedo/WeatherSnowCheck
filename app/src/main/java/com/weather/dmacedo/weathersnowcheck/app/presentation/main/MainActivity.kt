package com.weather.dmacedo.weathersnowcheck.app.presentation.main

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.menu.MenuBuilder
import android.support.v7.view.menu.MenuPopupHelper
import android.support.v7.widget.PopupMenu
import android.view.View
import com.squareup.picasso.Picasso
import com.weather.dmacedo.weathersnowcheck.R
import com.weather.dmacedo.weathersnowcheck.app.datasource.response.current.CurrentWeatherResponse
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

        main_loader.visibility = View.VISIBLE
        presenter.requestPermissions()

        main_weather_button.setOnClickListener({ showPopupMenu() })
    }


    //TODO: TESTAR EM TODAS APIS
    @SuppressLint("RestrictedApi")
    private fun showPopupMenu() {
        val popupMenu = PopupMenu(this, main_weather_button)
        popupMenu.menuInflater.inflate(R.menu.weather_conditions, popupMenu.menu)

        val popupHelper = MenuPopupHelper(this, popupMenu.menu as MenuBuilder, main_weather_button)
        popupHelper.setForceShowIcon(true)
        popupHelper.show()
    }

    private fun doInjections() {
        DaggerMainActivityComponent.builder()
                .appComponent((application as WeatherApplication).appComponent)
                .view(this)
                .build()
                .inject(this)
    }

    override fun showWeather(weather: CurrentWeatherResponse?) {
        main_loader.visibility = View.GONE

        main_city_text.text = weather?.name
        main_min_text.text = "min ${weather?.main?.temp_min}ºC"
        main_max_text.text = "max ${weather?.main?.temp_max}ºC"
        Picasso.get()
                .load("http://openweathermap.org/img/w/${weather?.weather?.get(0)?.icon}.png")
                .into(main_condition)
    }

    override fun showError(message: String?) {
        main_loader.visibility = View.GONE
        Snackbar.make(main_city_text, message!!, Snackbar.LENGTH_LONG)
                .showAsError()
    }

    override fun requestPermissions(permissionsNeeded: ArrayList<String>) {
        if (permissionsNeeded.isEmpty()) {
            presenter.getUserWeather()
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
                .setNeutralButton("Ok", { dialog, which ->
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
                presenter.getUserWeather()
            }
        }
    }


    //EXTENSION FUNCTION
    private fun Snackbar.showAsError() {
        (this.view as Snackbar.SnackbarLayout)
                .setBackgroundColor(ContextCompat.getColor(this.context, R.color.red_dark))
        this.show()
    }

    private fun Snackbar.showAsSuccess() {
        (this.view as Snackbar.SnackbarLayout)
                .setBackgroundColor(ContextCompat.getColor(this.context, R.color.blue_dark))
        this.show()
    }
}
