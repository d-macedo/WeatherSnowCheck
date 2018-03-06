package com.weather.dmacedo.weathersnowcheck.app.injection.component

import com.weather.dmacedo.weathersnowcheck.app.injection.module.AppModule
import com.weather.dmacedo.weathersnowcheck.app.internal.WeatherApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(application: WeatherApplication)
}