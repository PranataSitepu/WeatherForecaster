package com.pran.weatherforecaster.data.model.remote

import com.pran.weatherforecaster.data.model.local.FavoriteCityEntity
import com.pran.weatherforecaster.domain.model.City
import com.pran.weatherforecaster.domain.model.Weather
import com.pran.weatherforecaster.domain.util.TimeUtil

fun WeatherDto.toDomainModel(): Weather {
    return Weather(
        location = "",
        lat = lat,
        long = lon,
        current = current.toDomainModel(),
        dailies = dailies?.map { it.toDomainModel() },
    )
}

fun WeatherDto.CurrentWeatherDto.toDomainModel(): Weather.CurrentWeather {
    return Weather.CurrentWeather(
        date = TimeUtil.parseTimestampToDate(timestamp, TimeUtil.FULL_DATE_FORMAT).orEmpty(),
        temp = temp,
        humidity = humidity,
        windSpeed = windSpeed,
        descriptions = descriptions.first().description
    )
}

fun WeatherDto.DailyWeatherDto.toDomainModel(): Weather.DailyWeather {
    return Weather.DailyWeather(
        day = TimeUtil.parseTimestampToDate(timestamp, TimeUtil.SHORT_DAY_FORMAT).orEmpty(),
        date = TimeUtil.parseTimestampToDate(timestamp, TimeUtil.SHORT_DATE_FORMAT).orEmpty(),
        minTemp = temp.min,
        maxTemp = temp.max,
        humidity = humidity,
        windSpeed = windSpeed
    )
}

fun CityListDto.toDomainModel(): List<City> {
    return this.results.map {
        City(
            name = it.name,
            latitude = it.latitude,
            longitude = it.longitude
        )
    }
}

fun FavoriteCityEntity.toDomainModel(): City {
    return City(
        name = cityName,
        latitude = latitude,
        longitude = longitude,
    )
}

fun City.toDataModel(): FavoriteCityEntity {
    return FavoriteCityEntity(
        cityName = name,
        latitude = latitude,
        longitude = longitude
    )
}
