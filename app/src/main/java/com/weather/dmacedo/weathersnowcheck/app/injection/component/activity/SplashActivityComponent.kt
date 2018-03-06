package com.weather.dmacedo.weathersnowcheck.app.injection.component.activity

import com.weather.dmacedo.weathersnowcheck.app.injection.component.AppComponent
import com.weather.dmacedo.weathersnowcheck.app.injection.scope.ActivityScope
import com.weather.dmacedo.weathersnowcheck.app.presentation.splash.SplashActivity
import com.weather.dmacedo.weathersnowcheck.app.presentation.splash.SplashContract
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class])
interface SplashActivityComponent {

    fun inject(activity: SplashActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun view(view: SplashContract.View): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): SplashActivityComponent
    }
}