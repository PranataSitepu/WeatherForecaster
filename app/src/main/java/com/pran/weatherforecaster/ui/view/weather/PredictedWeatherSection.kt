package com.pran.weatherforecaster.ui.view.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pran.weatherforecaster.R
import com.pran.weatherforecaster.domain.model.Weather
import com.pran.weatherforecaster.ui.view.common.IconWithText

@Composable
fun PredictedWeatherSection(data: List<Weather.DailyWeather>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        data.forEach {
            DailyWeatherItem(data = it)
        }
    }
}

@Composable
private fun DailyWeatherItem(data: Weather.DailyWeather) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = data.day, fontSize = 14.sp)
        Text(text = data.date, fontSize = 7.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            IconWithText(
                icon = R.drawable.ic_arrow_up,
                text = stringResource(id = R.string.temperature_value, data.maxTemp),
                iconSize = 10.dp,
                textSize = 12.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            IconWithText(
                icon = R.drawable.ic_arrow_down,
                text = stringResource(id = R.string.temperature_value, data.minTemp),
                iconSize = 10.dp,
                textSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            IconWithText(
                icon = R.drawable.ic_humidity,
                text = stringResource(id = R.string.humidity_value, data.humidity),
                iconSize = 10.dp,
                textSize = 10.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            IconWithText(
                icon = R.drawable.ic_wind,
                text = stringResource(id = R.string.wind_value, data.windSpeed),
                iconSize = 10.dp,
                textSize = 10.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PredictedWeatherSectionPreview() {
    val dummyData = listOf(
        Weather.DailyWeather(
            day = "THU",
            date = "12 Oct",
            maxTemp = 36.7,
            minTemp = 27.3,
            humidity = 60,
            windSpeed = 1.6
        ),
        Weather.DailyWeather(
            day = "FRI",
            date = "13 Oct",
            maxTemp = 35.3,
            minTemp = 28.1,
            humidity = 35,
            windSpeed = 0.67
        ),
        Weather.DailyWeather(
            day = "SAT",
            date = "14 Oct",
            maxTemp = 37.1,
            minTemp = 25.9,
            humidity = 44,
            windSpeed = 1.12
        )
    )

    PredictedWeatherSection(data = dummyData)
}
