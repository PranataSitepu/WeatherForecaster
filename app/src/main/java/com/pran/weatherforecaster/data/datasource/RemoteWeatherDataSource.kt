package com.pran.weatherforecaster.data.datasource

import com.pran.weatherforecaster.data.model.WeatherDto
import com.pran.weatherforecaster.data.network.ApiInterface
import com.pran.weatherforecaster.data.network.handleRequestOnFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class RemoteWeatherDataSource @Inject constructor(
    private val apiInterface: ApiInterface
) : WeatherDataSource.Remote {

    override suspend fun getWeatherData(
        lat: Double,
        long: Double,
        appId: String,
        exclude: String
    ): Flow<WeatherDto> =
        handleRequestOnFlow {
            apiInterface.getCurrentWeather(lat = lat, long = long, appId = appId, exclude = exclude)
        }
}
