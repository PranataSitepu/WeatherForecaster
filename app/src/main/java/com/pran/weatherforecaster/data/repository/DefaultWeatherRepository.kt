package com.pran.weatherforecaster.data.repository

import com.pran.weatherforecaster.data.datasource.WeatherDataSource
import com.pran.weatherforecaster.data.model.toDomainModel
import com.pran.weatherforecaster.domain.model.Weather
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultWeatherRepository @Inject constructor(
    private val remoteWeatherDataSource: WeatherDataSource.Remote,
    private val localWeatherDataSource: WeatherDataSource.Local
) : WeatherRepository {

    override suspend fun getWeatherData(
        lat: Double,
        long: Double,
        appId: String,
        exclude: String
    ): Flow<Weather> {
        return remoteWeatherDataSource.getWeatherData(lat, long, appId, exclude).map {
            it.toDomainModel()
        }
    }
}
