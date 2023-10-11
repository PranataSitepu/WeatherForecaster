package com.pran.weatherforecaster.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pran.weatherforecaster.domain.model.Weather
import com.pran.weatherforecaster.domain.usecase.GetFavoriteWeatherUseCase
import com.pran.weatherforecaster.domain.usecase.GetWeatherUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getFavoriteWeatherUseCase: GetFavoriteWeatherUseCase
) : ViewModel() {

    private val _weatherState: MutableStateFlow<State> = MutableStateFlow(State.Initial)
    val weatherState: StateFlow<State>
        get() = _weatherState

    init {
        getWeatherData()
    }

    fun getWeatherData() {
        viewModelScope.launch {
            _weatherState.emit(State.Loading)
            getWeatherUseCase.execute(
                lat = -6.288364622987753,
                long = 106.92298059911394
            ).catch {
                _weatherState.emit(State.Error(it.message))
            }.collectLatest {
                _weatherState.emit(State.Success(it))
            }
        }
    }

    fun getFavoriteWeatherData() {
        viewModelScope.launch {
            _weatherState.emit(State.Loading)
            getFavoriteWeatherUseCase.execute(
                lat = -6.288364622987753,
                long = 106.92298059911394
            ).catch {
                _weatherState.emit(State.Error(it.message))
            }.collectLatest {
                _weatherState.emit(State.Success(it))
            }
        }
    }

    sealed class State {
        object Initial : State()
        object Loading : State()
        class Success(val data: Weather) : State()
        class Error(val message: String?) : State()
    }
}
