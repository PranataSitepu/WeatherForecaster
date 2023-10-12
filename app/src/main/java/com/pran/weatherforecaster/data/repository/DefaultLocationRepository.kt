package com.pran.weatherforecaster.data.repository

import com.pran.weatherforecaster.data.datasource.LocationDataSource
import com.pran.weatherforecaster.data.model.remote.toDataModel
import com.pran.weatherforecaster.data.model.remote.toDomainModel
import com.pran.weatherforecaster.domain.model.City
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultLocationRepository @Inject constructor(
    private val remoteLocationDataSource: LocationDataSource.Remote,
    private val localLocationDataSource: LocationDataSource.Local,
) : LocationRepository {
    override suspend fun getCityName(lat: Double, long: Double): Flow<String> {
        return remoteLocationDataSource.getCityName(lat, long).map {
            it.first().name
        }
    }

    override suspend fun getCityList(query: String): Flow<List<City>> {
        return remoteLocationDataSource.getCityList(query).map {
            it.toDomainModel()
        }
    }

    override suspend fun saveFavoriteCity(city: City) {
        localLocationDataSource.insertFavoriteCity(city.toDataModel())
    }

    override suspend fun loadFavoriteCity(): List<City> {
        return localLocationDataSource.getFavoriteCities().map {
            it.toDomainModel()
        }
    }

}
