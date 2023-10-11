package com.pran.weatherforecaster.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pran.weatherforecaster.domain.model.Weather
import com.pran.weatherforecaster.ui.view.favorites.FavoriteSection
import com.pran.weatherforecaster.ui.view.weather.WeatherSection

@Composable
fun HomeScreen(data: Weather = dummyData) {
    Column(modifier = Modifier.fillMaxSize()) {
        WeatherSection(data = data)
        FavoriteSection(data = dummyFavoriteData)
    }
}

private val dummyData = Weather(
    location = "Jakarta",
    current = Weather.CurrentWeather(
        date = "11 October 2023",
        temp = 28.41,
        humidity = 70,
        windSpeed = 1.23,
        descriptions = "Sedikit Berawan"
    ),
    dailies = listOf(
        Weather.DailyWeather(
            day = "THU",
            date = "12 Oct 2023",
            maxTemp = 36.7,
            minTemp = 27.3,
            humidity = 60,
            windSpeed = 1.6
        ),
        Weather.DailyWeather(
            day = "FRI",
            date = "13 Oct 2023",
            maxTemp = 35.3,
            minTemp = 28.1,
            humidity = 35,
            windSpeed = 0.67
        ),
        Weather.DailyWeather(
            day = "SAT",
            date = "14 Oct 2023",
            maxTemp = 37.1,
            minTemp = 25.9,
            humidity = 44,
            windSpeed = 1.12
        )
    )
)

val dummyFavoriteData = listOf(
    Weather(
        location = "Jakarta",
        current = Weather.CurrentWeather(
            date = "11 October 2023",
            temp = 28.41,
            humidity = 70,
            windSpeed = 1.23,
            descriptions = "Sedikit Berawan"
        ),
        dailies = null
    ),
    Weather(
        location = "Bandung",
        current = Weather.CurrentWeather(
            date = "11 October 2023",
            temp = 25.31,
            humidity = 90,
            windSpeed = 1.42,
            descriptions = "Berawan"
        ),
        dailies = null
    ),
    Weather(
        location = "Bali",
        current = Weather.CurrentWeather(
            date = "11 October 2023",
            temp = 26.82,
            humidity = 80,
            windSpeed = 1.66,
            descriptions = "Cerah"
        ),
        dailies = null
    )
)

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(dummyData)
}
