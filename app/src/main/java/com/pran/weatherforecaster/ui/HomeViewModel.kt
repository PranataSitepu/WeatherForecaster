package com.pran.weatherforecaster.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pran.weatherforecaster.domain.model.City
import com.pran.weatherforecaster.domain.model.Resource
import com.pran.weatherforecaster.domain.model.Weather
import com.pran.weatherforecaster.domain.usecase.DeleteFavoriteCityUseCase
import com.pran.weatherforecaster.domain.usecase.GetCityListUseCase
import com.pran.weatherforecaster.domain.usecase.GetFavoriteWeatherUseCase
import com.pran.weatherforecaster.domain.usecase.GetWeatherUseCase
import com.pran.weatherforecaster.domain.usecase.LoadFavoriteCityUseCase
import com.pran.weatherforecaster.domain.usecase.SaveFavoriteCityUseCase
import com.pran.weatherforecaster.ui.model.FavoriteWeatherSpec
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getFavoriteWeatherUseCase: GetFavoriteWeatherUseCase,
    private val getCityListUseCase: GetCityListUseCase,
    private val saveFavoriteCityUseCase: SaveFavoriteCityUseCase,
    private val loadFavoriteCityUseCase: LoadFavoriteCityUseCase,
    private val deleteFavoriteCityUseCase: DeleteFavoriteCityUseCase
) : ViewModel() {

    private val _weatherState: MutableStateFlow<Resource<Weather>> =
        MutableStateFlow(Resource.Initial())
    val weatherState: StateFlow<Resource<Weather>>
        get() = _weatherState

    private val _favoriteWeathers = MutableLiveData<List<FavoriteWeatherSpec>>()
    val favoriteWeathers: LiveData<List<FavoriteWeatherSpec>> = _favoriteWeathers

    private val _searchState: MutableStateFlow<Resource<List<City>>> =
        MutableStateFlow(Resource.Initial())
    val searchState: StateFlow<Resource<List<City>>>
        get() = _searchState

    var latitude = -6.21462
    var longitude = 106.84513

    init {
        getWeatherData()
        loadFavoriteCity()
    }

    fun getWeatherData() {
        viewModelScope.launch {
            _weatherState.emit(Resource.Loading())
            getWeatherUseCase.execute(
                lat = latitude,
                long = longitude
            ).catch {
                _weatherState.emit(Resource.Error(it))
            }.collectLatest {
                _weatherState.emit(Resource.Success(it))
            }
        }
    }

    fun getCityList(query: String) {
        viewModelScope.launch {
            _searchState.emit(Resource.Loading())
            getCityListUseCase.execute(query)
                .catch {
                    _searchState.emit(Resource.Error(it))
                    Log.e("ERROR", it.message.orEmpty())
                }
                .collectLatest {
                    _searchState.emit(Resource.Success(it))
                    Log.e("CITY", it.joinToString { city -> city.name })
                }
        }
    }

    fun saveFavoriteCity(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            saveFavoriteCityUseCase.execute(city)
            loadFavoriteCity()
        }
    }

    fun loadFavoriteCity() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = loadFavoriteCityUseCase.execute().map {
                FavoriteWeatherSpec(
                    city = it.name,
                    lat = it.latitude,
                    long = it.longitude
                )
            }
            _favoriteWeathers.postValue(result)
            result.forEachIndexed { index, favoriteWeatherSpec ->
                getFavoriteWeatherData(index, favoriteWeatherSpec)
            }
        }
    }

    fun deleteFavoriteCity(spec: FavoriteWeatherSpec) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteCityUseCase.execute(spec)
            loadFavoriteCity()
        }
    }

    private fun getFavoriteWeatherData(idx: Int, item: FavoriteWeatherSpec) {
        viewModelScope.launch {
            if (item.lat != null && item.long != null) {
                getFavoriteWeatherUseCase.execute(
                    lat = item.lat,
                    long = item.long
                ).catch {
                    it.printStackTrace()
                }.collectLatest { weather ->
                    _favoriteWeathers.value?.let {
                        val temporary = it.toMutableList()
                        temporary[idx] = temporary[idx].copy(
                            temp = weather.current.temp,
                            humidity = weather.current.humidity,
                            windSpeed = weather.current.windSpeed
                        )
                        _favoriteWeathers.postValue(temporary)
                    }
                }
            }
        }
    }
}
