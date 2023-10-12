package com.pran.weatherforecaster.data.datasource.remote

import com.pran.weatherforecaster.data.datasource.WeatherDataSource
import com.pran.weatherforecaster.data.model.remote.WeatherDto
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
        exclude: String
    ): Flow<WeatherDto> =
        handleRequestOnFlow {
            apiInterface.getCurrentWeather(lat = lat, long = long, exclude = exclude)
        }
}
