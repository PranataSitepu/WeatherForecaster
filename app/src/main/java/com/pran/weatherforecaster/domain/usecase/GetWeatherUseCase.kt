package com.pran.weatherforecaster.domain.usecase

import com.pran.weatherforecaster.data.repository.LocationRepository
import com.pran.weatherforecaster.data.repository.WeatherRepository
import com.pran.weatherforecaster.domain.model.Weather
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) {

    suspend fun execute(
        lat: Double,
        long: Double
    ): Flow<Weather> {
        return weatherRepository.getWeatherData(
            lat = lat,
            long = long,
            exclude = "minutely,hourly"
        ).flatMapMerge {
            locationRepository.getCityName(it.lat, it.long).map { cityName ->
                it.copy(location = cityName, dailies = it.dailies?.take(3))
            }
        }
    }
}
