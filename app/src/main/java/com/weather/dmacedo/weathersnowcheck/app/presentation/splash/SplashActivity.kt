package com.weather.dmacedo.weathersnowcheck.app.presentation.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.weather.dmacedo.weathersnowcheck.R
import com.weather.dmacedo.weathersnowcheck.app.injection.component.activity.DaggerSplashActivityComponent
import com.weather.dmacedo.weathersnowcheck.app.internal.WeatherApplication
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doInjections()
        presenter.getCurrentWeather()
    }

    private fun doInjections() {
        DaggerSplashActivityComponent.builder()
                .appComponent((application as WeatherApplication).appComponent)
                .view(this)
                .build()
                .inject(this)
    }


}