package com.weather.dmacedo.weathersnowcheck.app.injection.module

import com.weather.dmacedo.weathersnowcheck.app.datasource.remote.WeatherApi
import com.weather.dmacedo.weathersnowcheck.app.datasource.remote.WebServiceFactory
import dagger.Module
import dagger.Provides

@Module
class WebServiceModule {

    @Provides
    fun providesWebService(): WeatherApi {
        return WebServiceFactory.create()
    }

}