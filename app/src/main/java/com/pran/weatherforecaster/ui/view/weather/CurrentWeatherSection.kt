package com.pran.weatherforecaster.ui.view.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pran.weatherforecaster.R
import com.pran.weatherforecaster.domain.model.Weather
import com.pran.weatherforecaster.ui.view.common.CustomText
import com.pran.weatherforecaster.ui.view.common.IconWithText

@Composable
fun CurrentWeatherSection(data: Weather.CurrentWeather, location: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(text = data.date, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(
                modifier = Modifier.weight(0.5f),
                text = stringResource(id = R.string.temperature_value, data.temp),
                fontSize = 84.sp
            )
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(8.dp)
            ) {
                CustomText(text = data.descriptions, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconWithText(
                        icon = R.drawable.ic_humidity,
                        text = stringResource(
                            id = R.string.humidity_value,
                            data.humidity
                        ),
                        iconSize = 16.dp,
                        textSize = 16.sp
                    )
                    IconWithText(
                        icon = R.drawable.ic_wind,
                        text = stringResource(id = R.string.wind_value, data.windSpeed),
                        iconSize = 16.dp,
                        textSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                CustomText(text = location, fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CurrentWeatherSectionPreview() {
    val dummyData = Weather.CurrentWeather(
        date = "11 October 2023",
        temp = 28.41,
        humidity = 70,
        windSpeed = 1.23,
        descriptions = "Sedikit Berawan"
    )
    CurrentWeatherSection(data = dummyData, location = "Jakarta")
}


