package com.pran.weatherforecaster.data.repository

import com.pran.weatherforecaster.domain.model.City
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun getCityName(
        lat: Double,
        long: Double,
    ): Flow<String>

    suspend fun getCityList(
        query: String
    ): Flow<List<City>>

    suspend fun saveFavoriteCity(
        city: City
    )

    suspend fun loadFavoriteCity(): List<City>
}
