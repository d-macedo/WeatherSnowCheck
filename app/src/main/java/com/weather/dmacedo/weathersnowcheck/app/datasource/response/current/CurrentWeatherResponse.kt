package com.weather.dmacedo.weathersnowcheck.app.datasource.response.current

data class CurrentWeatherResponse(
        val coord: CoordinateResponse,
        val sys: SysResponse,
        val weather: Array<WeatherResponse>,
        val main: MainResponse,
        val base: String,
        val wind: WindResponse,
        val clouds: CloudsResponse,
        val dt: String,
        val id: Long,
        val name: String,
        val cod: Long
)

data class SysResponse(
        val country: String,
        val sunrise: Long,
        val sunset: Long
)

data class CloudsResponse(val all: Long)

data class WindResponse(val speed: Double, val deg: Double)

data class MainResponse (
        val temp: Double,
        val pressure: Double,
        val humidity: Long,
        val temp_min: Double,
        val temp_max: Double,
        val sea_level: Double,
        val grnd_level: Double
)

data class WeatherResponse(
        val id : Long,
        val main : String,
        val description : String,
        val icon : String
)

data class CoordinateResponse (
    val lon : Double,
    val lat : Double
)
