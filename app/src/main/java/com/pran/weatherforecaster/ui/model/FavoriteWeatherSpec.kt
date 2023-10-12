package com.pran.weatherforecaster.ui.model

data class FavoriteWeatherSpec(
    val city: String,
    val lat: Double? = null,
    val long: Double? = null,
    val temp: Double? = null,
    val humidity: Int? = null,
    val windSpeed: Double? = null
)
