package com.pran.weatherforecaster.domain.model

data class FavoriteCityWeather(
    val location: String,
    val date: String,
    val temp: Double,
    val humidity: Int,
    val windSpeed: Double,
    val descriptions: String
)
