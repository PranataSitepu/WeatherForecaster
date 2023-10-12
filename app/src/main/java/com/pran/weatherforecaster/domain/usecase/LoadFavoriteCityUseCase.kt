package com.pran.weatherforecaster.domain.usecase

import com.pran.weatherforecaster.domain.repository.LocationRepository
import com.pran.weatherforecaster.domain.model.City
import javax.inject.Inject

class LoadFavoriteCityUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    suspend fun execute(): List<City> {
        return locationRepository.loadFavoriteCity()
    }
}
