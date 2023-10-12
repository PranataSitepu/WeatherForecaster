package com.pran.weatherforecaster.data.model.remote

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WeatherDto(
    val lat: Double,
    val lon: Double,
    @SerializedName("current") val current: CurrentWeatherDto,
    @SerializedName("daily") val dailies: List<DailyWeatherDto>?
) {
    @Keep
    data class CurrentWeatherDto(
        @SerializedName("dt") val timestamp: Long,
        val temp: Double,
        val humidity: Int,
        @SerializedName("wind_speed") val windSpeed: Double,
        @SerializedName("weather") val descriptions: List<DescriptionDto>
    )

    @Keep
    data class DescriptionDto(
        val main: String,
        val description: String
    )

    @Keep
    data class DailyWeatherDto(
        @SerializedName("dt") val timestamp: Long,
        val temp: TemperatureDto,
        val humidity: Int,
        @SerializedName("wind_speed") val windSpeed: Double
    )

    @Keep
    data class TemperatureDto(
        val min: Double,
        val max: Double,
    )
}
