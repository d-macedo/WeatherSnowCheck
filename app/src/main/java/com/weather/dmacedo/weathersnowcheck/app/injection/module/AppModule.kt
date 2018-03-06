package com.weather.dmacedo.weathersnowcheck.app.injection.module

import com.weather.dmacedo.weathersnowcheck.app.internal.WeatherApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: WeatherApplication) {

    @Provides
    @Singleton
    fun provideApplication() = app
}