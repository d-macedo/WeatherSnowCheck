package com.weather.dmacedo.weathersnowcheck.app.injection.module

import android.content.Context
import com.weather.dmacedo.weathersnowcheck.app.internal.WeatherApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: WeatherApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = app
}