package com.pran.weatherforecaster.domain.usecase

import com.pran.weatherforecaster.data.repository.LocationRepository
import com.pran.weatherforecaster.ui.model.FavoriteWeatherSpec
import javax.inject.Inject

class LoadFavoriteCityUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    suspend fun execute(): List<FavoriteWeatherSpec> {
        return locationRepository.loadFavoriteCity().map {
            FavoriteWeatherSpec(
                city = it.name,
                lat = it.latitude,
                long = it.longitude
            )
        }
    }
}
