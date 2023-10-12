package com.pran.weatherforecaster.data.network

import com.pran.weatherforecaster.BuildConfig
import com.pran.weatherforecaster.data.model.remote.CityListDto
import com.pran.weatherforecaster.data.model.remote.CityNameDto
import com.pran.weatherforecaster.data.model.remote.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {

    @GET("/data/3.0/onecall")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("appId") appId: String = BuildConfig.OPEN_WEATHER_ID,
        @Query("exclude") exclude: String = "minutely,hourly",
        @Query("units") units: String = "metric",
        @Query("lang") language: String = "id",
    ): WeatherDto

    @GET("/geo/1.0/reverse")
    suspend fun getCityName(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("appId") appId: String = BuildConfig.OPEN_WEATHER_ID,
    ): List<CityNameDto>

    @GET("https://parseapi.back4app.com/classes/Indonesiacities_Indonesia_Cities_Database")
    suspend fun getCityList(
        @Header("X-Parse-Application-Id") id: String = BuildConfig.BACK4APP_ID,
        @Header("X-Parse-REST-API-Key") key: String = BuildConfig.BACK4APP_KEY,
        @Query("excludeKeys") excludeKeys: String = "population,elevation,timezone",
        @Query("where", encoded = true) query: String,
    ): CityListDto
}
