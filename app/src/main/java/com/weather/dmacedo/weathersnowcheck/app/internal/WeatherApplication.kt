package com.weather.dmacedo.weathersnowcheck.app.internal

import android.app.Application
import com.weather.dmacedo.weathersnowcheck.app.injection.component.AppComponent
import com.weather.dmacedo.weathersnowcheck.app.injection.component.DaggerAppComponent
import com.weather.dmacedo.weathersnowcheck.app.injection.module.AppModule

class WeatherApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}