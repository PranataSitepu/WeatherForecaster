package com.pran.weatherforecaster.data.repository

import com.pran.weatherforecaster.data.datasource.WeatherDataSource
import com.pran.weatherforecaster.data.model.remote.toDomainModel
import com.pran.weatherforecaster.domain.model.Weather
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultWeatherRepository @Inject constructor(
    private val remoteWeatherDataSource: WeatherDataSource.Remote
) : WeatherRepository {

    override suspend fun getWeatherData(
        lat: Double,
        long: Double,
        exclude: String
    ): Flow<Weather> {
        return remoteWeatherDataSource.getWeatherData(lat, long, exclude).map {
            it.toDomainModel()
        }
    }
}
