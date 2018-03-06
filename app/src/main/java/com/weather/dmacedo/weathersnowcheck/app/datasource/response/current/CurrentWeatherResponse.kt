package com.weather.dmacedo.weathersnowcheck.app.datasource.response.current

data class CurrentWeatherResponse(
        val coord: CoordinateResponse,
        val weather: WeatherResponse,
        val base: String,
        val main: MainResponse,
        val wind: WindResponse,
        val clouds: CloudsResponse,
        val dt: String,
        val sys: SysResponse,
        val id: Long,
        val name: String,
        val cod: Long
)

data class SysResponse(
        val message: Double,
        val country: String,
        val sunrise: Long,
        val sunset: Long
)

data class CloudsResponse(val all: Long)

data class WindResponse(val speed: Double, val deg: Long)

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
