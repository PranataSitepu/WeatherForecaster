package com.pran.weatherforecaster.domain.usecase

import com.pran.weatherforecaster.data.repository.LocationRepository
import com.pran.weatherforecaster.ui.model.FavoriteWeatherSpec
import javax.inject.Inject

class DeleteFavoriteCityUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    suspend fun execute(spec: FavoriteWeatherSpec) {
        locationRepository.deleteFavoriteCity(spec.city)
    }
}
