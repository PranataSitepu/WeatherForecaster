package com.pran.weatherforecaster.ui.view.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.NoOpUpdate
import com.pran.weatherforecaster.domain.model.City
import com.pran.weatherforecaster.domain.model.Resource
import com.pran.weatherforecaster.ui.view.common.CustomText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLocationScreen(
    searchState: Resource<List<City>>,
    onSearch: (String) -> Unit,
    onSelected: (City) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Column {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                onSearch(it)
            },
            active = true,
            onActiveChange = { },
            placeholder = {
                Text(text = "Search City")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            trailingIcon = {
                if (text.isNotBlank()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Icon",
                        Modifier.clickable {
                            text = ""
                        }
                    )
                }
            },
        ) {
            when (searchState) {
                is Resource.Loading -> {
                    Column(Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                }

                is Resource.Error -> {}
                is Resource.Success -> {
                    searchState.data.forEach {
                        Item(city = it,
                            onSelected = { picked ->
                                onSelected(picked)
                            }
                        )
                    }
                }

                else -> NoOpUpdate
            }
        }
    }
}

@Composable
private fun Item(
    city: City,
    onSelected: (City) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelected(city) }
    ) {
        CustomText(
            modifier = Modifier.padding(16.dp),
            text = city.name
        )
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchLocationScreenPreview() {
    SearchLocationScreen(
        searchState = Resource.Success(
            listOf(
                City(
                    name = "Bandung",
                    latitude = 0.0,
                    longitude = 0.0
                ),
                City(
                    name = "Bandar Lampung",
                    latitude = 0.0,
                    longitude = 0.0
                ),
                City(
                    name = "Banda",
                    latitude = 0.0,
                    longitude = 0.0
                )
            )
        ),
        onSearch = {},
        onSelected = {}
    )
}
