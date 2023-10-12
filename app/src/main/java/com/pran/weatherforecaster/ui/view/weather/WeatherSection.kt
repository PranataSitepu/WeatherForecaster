package com.pran.weatherforecaster.ui.view.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pran.weatherforecaster.domain.model.Weather

@Composable
fun WeatherSection(data: Weather) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        color = Color.LightGray
    ) {
        Column(modifier = Modifier.padding(vertical = 50.dp)) {
            CurrentWeatherSection(data = data.current, location = data.location)
            Spacer(modifier = Modifier.height(32.dp))
            data.dailies?.let {
                PredictedWeatherSection(
                    data = it
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun WeatherSectionPreview() {
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

    WeatherSection(data = dummyData)
}
