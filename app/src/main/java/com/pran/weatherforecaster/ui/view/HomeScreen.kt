package com.pran.weatherforecaster.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.NoOpUpdate
import com.pran.weatherforecaster.domain.model.Resource
import com.pran.weatherforecaster.domain.model.Weather
import com.pran.weatherforecaster.ui.model.FavoriteWeatherSpec
import com.pran.weatherforecaster.ui.view.favorites.FavoriteSection
import com.pran.weatherforecaster.ui.view.weather.WeatherSection

@Composable
fun HomeScreen(
    weatherState: Resource<Weather>,
    favoriteWeather: List<FavoriteWeatherSpec>?,
    onNavigateToSearch: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        when (weatherState) {
            is Resource.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is Resource.Success -> {
                WeatherSection(data = weatherState.data)
            }

            else -> NoOpUpdate
        }
        FavoriteSection(data = favoriteWeather, onNavigateToSearch = onNavigateToSearch)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    val dummyData = Weather(
        location = "Jakarta",
        lat = 0.0,
        long = 0.0,
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
        FavoriteWeatherSpec(
            city = "Jakarta",
            lat = 0.0,
            long = 0.0,
            temp = 28.41,
            humidity = 70,
            windSpeed = 1.23,
        ),
        FavoriteWeatherSpec(
            city = "Bandung",
            lat = 0.0,
            long = 0.0,
            temp = 25.31,
            humidity = 90,
            windSpeed = 1.42,
        ),
        FavoriteWeatherSpec(
            city = "Bali",
            lat = 0.0,
            long = 0.0,
            temp = 26.82,
            humidity = 80,
            windSpeed = 1.66,
        ),
    )
    HomeScreen(
        weatherState = Resource.Success(dummyData),
        favoriteWeather = dummyFavoriteData,
        onNavigateToSearch = {}
    )
}
