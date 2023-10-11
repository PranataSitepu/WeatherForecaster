package com.pran.weatherforecaster.data.datasource

import com.pran.weatherforecaster.data.model.WeatherDto
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {

    interface Remote {
        suspend fun getWeatherData(
            lat: Double,
            long: Double,
            appId: String,
            exclude: String
        ): Flow<WeatherDto>
    }


    interface Local {
    }
}
