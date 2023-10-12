package com.pran.weatherforecaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pran.weatherforecaster.ui.HomeViewModel
import com.pran.weatherforecaster.ui.theme.WeatherForecasterTheme
import com.pran.weatherforecaster.ui.view.HomeScreen
import com.pran.weatherforecaster.ui.view.search.SearchLocationScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherForecasterTheme {
                val navController = rememberNavController()
                val weatherState = viewModel.weatherState.collectAsState().value
                val searchState = viewModel.searchState.collectAsState().value
                val favoriteWeather = viewModel.favoriteWeathers.observeAsState().value

                Scaffold {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") {
                                HomeScreen(
                                    weatherState = weatherState,
                                    favoriteWeather = favoriteWeather,
                                    onNavigateToSearch = { navController.navigate("search") },
                                    onRetry = {
                                        viewModel.getWeatherData()
                                        viewModel.loadFavoriteCity()
                                    }
                                )
                            }
                            composable("search") {
                                SearchLocationScreen(
                                    searchState = searchState,
                                    onSearch = { viewModel.getCityList(it) },
                                    onSelected = {
                                        viewModel.saveFavoriteCity(it)
                                        viewModel.getWeatherData()
                                        viewModel.loadFavoriteCity()
                                        navController.popBackStack()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}
