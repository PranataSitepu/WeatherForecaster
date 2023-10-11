package com.pran.weatherforecaster.data.model

import com.pran.weatherforecaster.domain.model.Weather

fun WeatherDto.toDomainModel(): Weather {
    return Weather(
        location = "Bekasi",
        current = current.toDomainModel(),
        dailies = dailies?.map { it.toDomainModel() },
    )
}

fun WeatherDto.CurrentWeatherDto.toDomainModel(): Weather.CurrentWeather {
    return Weather.CurrentWeather(
        date = timestamp.toString(),
        temp = temp,
        humidity = humidity,
        windSpeed = windSpeed,
        descriptions = descriptions.first().description
    )
}

fun WeatherDto.DailyWeatherDto.toDomainModel(): Weather.DailyWeather {
    return Weather.DailyWeather(
        day = timestamp.toString(),
        date = timestamp.toString(),
        minTemp = temp.min,
        maxTemp = temp.max,
        humidity = humidity,
        windSpeed = windSpeed
    )
}
