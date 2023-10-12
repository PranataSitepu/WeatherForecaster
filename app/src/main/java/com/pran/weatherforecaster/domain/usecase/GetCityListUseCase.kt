package com.pran.weatherforecaster.domain.usecase

import com.pran.weatherforecaster.domain.repository.LocationRepository
import com.pran.weatherforecaster.domain.model.City
import java.net.URLEncoder
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetCityListUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    suspend fun execute(cityName: String): Flow<List<City>> {
        return withContext(Dispatchers.IO) {
            val cityQuery = cityName.lowercase().split(" ").map {
                it.replaceFirstChar { it.uppercase() }
            }.joinToString(" ")
            val query = URLEncoder.encode(
                """
            {
                "asciiname": {
                    "${'$'}regex": "$cityQuery"
                }
            }
            """.trimIndent(), "utf-8"
            )
            locationRepository.getCityList(query)
        }
    }
}
