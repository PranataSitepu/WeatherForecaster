package com.pran.weatherforecaster.data.repository

import com.pran.weatherforecaster.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeatherData(
        lat: Double,
        long: Double,
        exclude: String
    ): Flow<Weather>
}
