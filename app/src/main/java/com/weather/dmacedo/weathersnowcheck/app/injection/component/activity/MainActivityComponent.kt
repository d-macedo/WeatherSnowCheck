package com.weather.dmacedo.weathersnowcheck.app.injection.component.activity

import com.weather.dmacedo.weathersnowcheck.app.injection.component.AppComponent
import com.weather.dmacedo.weathersnowcheck.app.injection.module.WebServiceModule
import com.weather.dmacedo.weathersnowcheck.app.injection.scope.ActivityScope
import com.weather.dmacedo.weathersnowcheck.app.presentation.main.MainActivity
import com.weather.dmacedo.weathersnowcheck.app.presentation.main.MainContract
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class],
        modules = [WebServiceModule::class])
interface MainActivityComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun view(view: MainContract.View): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): MainActivityComponent
    }
}