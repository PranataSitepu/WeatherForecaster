package com.pran.weatherforecaster.data.model.remote

import androidx.annotation.Keep

@Keep
data class CityListDto(
    val results: List<CityDto>,
) {
    data class CityDto(
        val name: String,
        val latitude: Double,
        val longitude: Double,
    )
}
