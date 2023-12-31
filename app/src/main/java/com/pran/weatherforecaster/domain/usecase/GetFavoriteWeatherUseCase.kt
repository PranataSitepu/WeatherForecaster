package com.pran.weatherforecaster.domain.usecase

import com.pran.weatherforecaster.domain.repository.WeatherRepository
import com.pran.weatherforecaster.domain.model.Weather
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetFavoriteWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun execute(
        lat: Double,
        long: Double
    ): Flow<Weather> {
        return weatherRepository.getWeatherData(
            lat = lat,
            long = long,
            exclude = "minutely,hourly,daily"
        )
    }
}
