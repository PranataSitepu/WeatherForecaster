package com.pran.weatherforecaster.data.datasource

import com.pran.weatherforecaster.data.model.local.FavoriteCityEntity
import com.pran.weatherforecaster.data.model.remote.CityListDto
import com.pran.weatherforecaster.data.model.remote.CityNameDto
import kotlinx.coroutines.flow.Flow

interface LocationDataSource {

    interface Remote {
        suspend fun getCityName(
            lat: Double,
            long: Double,
        ): Flow<List<CityNameDto>>

        suspend fun getCityList(
            query: String
        ): Flow<CityListDto>
    }


    interface Local {
        fun getFavoriteCities(): List<FavoriteCityEntity>
        fun insertFavoriteCity(city: FavoriteCityEntity)
        fun deleteFavoriteCity(name: String)
    }
}
