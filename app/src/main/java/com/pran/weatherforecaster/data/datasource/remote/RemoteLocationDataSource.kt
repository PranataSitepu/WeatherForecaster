package com.pran.weatherforecaster.data.datasource.remote

import com.pran.weatherforecaster.data.datasource.LocationDataSource
import com.pran.weatherforecaster.data.model.remote.CityListDto
import com.pran.weatherforecaster.data.model.remote.CityNameDto
import com.pran.weatherforecaster.data.network.ApiInterface
import com.pran.weatherforecaster.data.network.handleRequestOnFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class RemoteLocationDataSource @Inject constructor(
    private val apiInterface: ApiInterface
) : LocationDataSource.Remote {
    override suspend fun getCityName(
        lat: Double,
        long: Double,
    ): Flow<List<CityNameDto>> =
        handleRequestOnFlow {
            apiInterface.getCityName(lat, long)
        }

    override suspend fun getCityList(query: String): Flow<CityListDto> =
        handleRequestOnFlow {
            apiInterface.getCityList(query = query)

        }
}
