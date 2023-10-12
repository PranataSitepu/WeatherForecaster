package com.pran.weatherforecaster.data.datasource.remote

import com.pran.weatherforecaster.data.datasource.LocationDataSource
import com.pran.weatherforecaster.data.db.LocationDao
import com.pran.weatherforecaster.data.model.local.FavoriteCityEntity
import javax.inject.Inject

class LocalLocationDataSource @Inject constructor(
    private val locationDao: LocationDao
) : LocationDataSource.Local {
    override fun getFavoriteCities(): List<FavoriteCityEntity> {
        return locationDao.getAll()
    }

    override fun insertFavoriteCity(city: FavoriteCityEntity) {
        locationDao.insert(city)
    }

    override fun deleteFavoriteCity(name: String) {
        locationDao.deleteByName(name)
    }

}
