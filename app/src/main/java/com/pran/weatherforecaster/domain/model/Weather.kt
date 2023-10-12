package com.pran.weatherforecaster.domain.model

data class Weather(
    val location: String,
    val lat: Double,
    val long: Double,
    val current: CurrentWeather,
    val dailies: List<DailyWeather>?
) {
    data class CurrentWeather(
        val date: String,
        val temp: Double,
        val humidity: Int,
        val windSpeed: Double,
        val descriptions: String
    )

    data class DailyWeather(
        val day: String,
        val date: String,
        val minTemp: Double,
        val maxTemp: Double,
        val humidity: Int,
        val windSpeed: Double,
    )
}
