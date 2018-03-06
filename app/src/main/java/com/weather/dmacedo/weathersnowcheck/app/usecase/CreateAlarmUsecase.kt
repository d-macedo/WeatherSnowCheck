package com.weather.dmacedo.weathersnowcheck.app.usecase

import com.weather.dmacedo.weathersnowcheck.app.datasource.repository.AlarmRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreateAlarmUsecase @Inject constructor(private val alarmRepository: AlarmRepository) {

    fun execute(hour: Int,
                minutes: Int,
                alarmTag: String = "App Alarm"): Observable<Void> {
        return alarmRepository.createAlarm(hour, minutes, alarmTag)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }
}