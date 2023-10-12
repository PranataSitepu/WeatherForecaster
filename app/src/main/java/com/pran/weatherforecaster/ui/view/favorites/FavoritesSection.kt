package com.pran.weatherforecaster.ui.view.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pran.weatherforecaster.R
import com.pran.weatherforecaster.ui.model.FavoriteWeatherSpec
import com.pran.weatherforecaster.ui.view.common.CustomText

@Composable
fun FavoriteSection(
    data: List<FavoriteWeatherSpec>?,
    onNavigateToSearch: () -> Unit,
    onSelect: (FavoriteWeatherSpec) -> Unit,
    onDelete: (FavoriteWeatherSpec) -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        CustomText(text = stringResource(id = R.string.favorite), fontSize = 16.sp)
        LazyColumn(
            modifier = Modifier.padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            data?.let { list ->
                items(list.size) {
                    FavoriteItem(
                        data = list[it],
                        onSelect = onSelect,
                        onDelete = onDelete
                    )
                }
            }
            item {
                AddFavoriteItem(onNavigateToSearch)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun FavoriteSectionPreview() {
    val dummyData = listOf(
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
    FavoriteSection(dummyData, {}, {}, {})
}
