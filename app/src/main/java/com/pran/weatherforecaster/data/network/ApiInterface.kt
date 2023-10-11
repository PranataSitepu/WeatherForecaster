package com.pran.weatherforecaster.data.network

import com.pran.weatherforecaster.data.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api.openweathermap.org/data/3.0/onecall")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("appId") appId: String,
        @Query("exclude") exclude: String = "minutely,hourly",
        @Query("units") units: String = "metric",
        @Query("lang") language: String = "id",
    ): WeatherDto
}
