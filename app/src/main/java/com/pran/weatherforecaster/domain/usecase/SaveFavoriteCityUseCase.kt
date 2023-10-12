package com.pran.weatherforecaster.domain.usecase

import com.pran.weatherforecaster.domain.repository.LocationRepository
import com.pran.weatherforecaster.domain.model.City
import javax.inject.Inject

class SaveFavoriteCityUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    suspend fun execute(city: City) {
        locationRepository.saveFavoriteCity(city)
    }
}
