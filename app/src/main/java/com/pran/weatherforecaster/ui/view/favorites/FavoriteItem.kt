package com.pran.weatherforecaster.ui.view.favorites

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pran.weatherforecaster.R
import com.pran.weatherforecaster.domain.model.Weather
import com.pran.weatherforecaster.ui.view.common.IconWithText

@Composable
fun FavoriteItem(data: Weather.CurrentWeather, location: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(modifier = Modifier.weight(0.4f), text = location, fontSize = 24.sp)
            Row(
                modifier = Modifier.weight(0.6f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconWithText(
                    icon = R.drawable.ic_temperature,
                    text = stringResource(id = R.string.temperature_value, data.temp),
                    iconSize = 12.dp,
                    textSize = 14.sp
                )

                IconWithText(
                    icon = R.drawable.ic_humidity,
                    text = stringResource(id = R.string.humidity_value, data.humidity),
                    iconSize = 12.dp,
                    textSize = 14.sp
                )

                IconWithText(
                    icon = R.drawable.ic_wind,
                    text = stringResource(id = R.string.wind_value, data.windSpeed),
                    iconSize = 12.dp,
                    textSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun FavoriteItemPreview() {
    val dummyData = Weather.CurrentWeather(
        date = "11 October 2023",
        temp = 28.41,
        humidity = 70,
        windSpeed = 1.23,
        descriptions = "Sedikit Berawan"
    )
    FavoriteItem(data = dummyData, location = "Jakarta")
}
