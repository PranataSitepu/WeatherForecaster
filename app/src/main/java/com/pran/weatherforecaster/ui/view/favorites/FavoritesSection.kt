package com.pran.weatherforecaster.ui.view.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pran.weatherforecaster.domain.model.Weather
import com.pran.weatherforecaster.ui.util.MAX_FAVORITE_CITY

@Composable
fun FavoriteSection(data: List<Weather>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Favorites", fontSize = 16.sp)
        LazyColumn(
            modifier = Modifier.padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(data.size) {
                FavoriteItem(data = data[it].current, location = data[it].location)
            }
            if (data.size < MAX_FAVORITE_CITY) {
                item {
                    AddFavoriteItem()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun FavoriteSectionPreview() {
    val dummyData = listOf(
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
    FavoriteSection(dummyData)
}
