package com.pran.weatherforecaster.domain.usecase

import com.pran.weatherforecaster.data.repository.WeatherRepository
import com.pran.weatherforecaster.domain.model.Weather
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun execute(
        lat: Double,
        long: Double
    ): Flow<Weather> {
        return weatherRepository.getWeatherData(
            lat = lat,
            long = long,
            appId = "50e7842f7455c50e480f37195614149a",
            exclude = "minutely,hourly"
        )
    }
}
