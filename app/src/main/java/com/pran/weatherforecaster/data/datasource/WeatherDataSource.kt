package com.pran.weatherforecaster.data.datasource

import com.pran.weatherforecaster.data.model.remote.WeatherDto
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {

    interface Remote {
        suspend fun getWeatherData(
            lat: Double,
            long: Double,
            exclude: String
        ): Flow<WeatherDto>
    }

}
