package com.weather.dmacedo.weathersnowcheck.app.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.weather.dmacedo.weathersnowcheck.R
import com.weather.dmacedo.weathersnowcheck.app.injection.component.activity.DaggerSplashActivityComponent
import com.weather.dmacedo.weathersnowcheck.app.internal.WeatherApplication
import com.weather.dmacedo.weathersnowcheck.app.presentation.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        doInjections()
//        presenter.getCurrentWeather()

        //todo: GO TO ONBOARDING IF IT'S THE 1ST TIME

        startMainActivity()
    }

    private fun startMainActivity() {
        imageview_splash.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)
    }

    private fun doInjections() {
        DaggerSplashActivityComponent.builder()
                .appComponent((application as WeatherApplication).appComponent)
                .view(this)
                .build()
                .inject(this)
    }
}