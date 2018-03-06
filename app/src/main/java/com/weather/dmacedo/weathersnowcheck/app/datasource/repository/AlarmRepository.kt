package com.weather.dmacedo.weathersnowcheck.app.datasource.repository

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import io.reactivex.Observable
import javax.inject.Inject

class AlarmRepository @Inject constructor(private val context: Context) {

    /*
        EXTRA_HOUR (optional): The hour of the alarm being set.
        EXTRA_MINUTES (optional): The minutes of the alarm being set.
        EXTRA_DAYS (optional): Weekdays for repeating alarm.
        EXTRA_MESSAGE (optional): A custom message for the alarm.
        EXTRA_RINGTONE (optional): A ringtone to play with this alarm.
        EXTRA_VIBRATE (optional): Whether or not to activate the device vibrator for this alarm.
        EXTRA_SKIP_UI (optional): Whether or not to display an activity for setting this alarm.
     */


    fun createAlarm(hour: Int,
                    minutes: Int,
                    alarmTag: String): Observable<Void> {
        try {
            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_HOUR, hour)
                putExtra(AlarmClock.EXTRA_MINUTES, minutes)
                putExtra(AlarmClock.EXTRA_MESSAGE, alarmTag)
                putExtra(AlarmClock.EXTRA_SKIP_UI, true)
            }

            context.startActivity(intent)

            return Observable.empty<Void>()
        } catch (exception: Exception) {
            return Observable.error(exception)
        }
    }
}