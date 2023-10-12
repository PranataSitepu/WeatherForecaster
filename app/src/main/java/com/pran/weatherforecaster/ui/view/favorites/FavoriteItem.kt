package com.pran.weatherforecaster.ui.view.favorites

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pran.weatherforecaster.R
import com.pran.weatherforecaster.ui.model.FavoriteWeatherSpec
import com.pran.weatherforecaster.ui.view.common.CustomText
import com.pran.weatherforecaster.ui.view.common.IconWithText

@Composable
fun FavoriteItem(
    data: FavoriteWeatherSpec,
    onSelect: (FavoriteWeatherSpec) -> Unit,
    onDelete: (FavoriteWeatherSpec) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect(data) },
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomText(modifier = Modifier.weight(0.4f), text = data.city, fontSize = 24.sp)
            Row(
                modifier = Modifier.weight(0.5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconWithText(
                    icon = R.drawable.ic_temperature,
                    text =
                    data.temp?.let { stringResource(id = R.string.temperature_value, it) } ?: "-",
                    iconSize = 12.dp,
                    textSize = 14.sp
                )

                IconWithText(
                    icon = R.drawable.ic_humidity,
                    text =
                    data.humidity?.let { stringResource(id = R.string.humidity_value, it) } ?: "-",
                    iconSize = 12.dp,
                    textSize = 14.sp
                )

                IconWithText(
                    icon = R.drawable.ic_wind,
                    text =
                    data.windSpeed?.let { stringResource(id = R.string.wind_value, it) } ?: "-",
                    iconSize = 12.dp,
                    textSize = 14.sp
                )
            }
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Icon",
                tint = Color.Red,
                modifier = Modifier.clickable {
                    onDelete(data)
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun FavoriteItemPreview() {
    val dummyData = FavoriteWeatherSpec(
        city = "Jakarta",
        temp = 28.41,
        humidity = 70,
        windSpeed = 1.23,
    )
    FavoriteItem(data = dummyData,
        onSelect = {},
        onDelete = {})
}
