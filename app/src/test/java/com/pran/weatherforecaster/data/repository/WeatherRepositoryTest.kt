package com.pran.weatherforecaster.data.repository

import com.google.common.truth.Truth
import com.pran.weatherforecaster.data.datasource.remote.RemoteWeatherDataSource
import com.pran.weatherforecaster.data.model.remote.WeatherDto
import com.pran.weatherforecaster.data.network.ApiInterface
import com.pran.weatherforecaster.domain.model.Weather
import com.pran.weatherforecaster.domain.repository.WeatherRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class WeatherRepositoryTest {

    @MockK
    private lateinit var apiInterface: ApiInterface

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var remoteDataSource: RemoteWeatherDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        remoteDataSource = RemoteWeatherDataSource(apiInterface)
        weatherRepository = DefaultWeatherRepository(remoteDataSource)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get weather data available return data`() {
        runTest {
            // Given
            val lat = 0.0
            val lon = 0.0
            val exclude = "exclude"
            val response = WeatherDto(
                lat = lat,
                lon = lon,
                current = WeatherDto.CurrentWeatherDto(
                    timestamp = 0,
                    temp = 27.0,
                    humidity = 70,
                    windSpeed = 1.0,
                    descriptions = listOf(
                        WeatherDto.DescriptionDto(
                            main = "",
                            description = "test"
                        )
                    )
                ),
                dailies = listOf(
                    WeatherDto.DailyWeatherDto(
                        timestamp = 0,
                        temp = WeatherDto.TemperatureDto(min = 25.0, max = 30.0),
                        humidity = 80,
                        windSpeed = 1.1
                    )
                )
            )
            val expectedResult = Weather(
                location = "",
                lat = lat,
                long = lon,
                current = Weather.CurrentWeather(
                    date = "01 January 1970",
                    temp = 27.0,
                    humidity = 70,
                    windSpeed = 1.0,
                    descriptions = "test",
                ),
                dailies = listOf(
                    Weather.DailyWeather(
                        day = "Thu",
                        date = "01 Jan 1970",
                        minTemp = 25.0,
                        maxTemp = 30.0,
                        humidity = 80,
                        windSpeed = 1.1
                    )
                )
            )

            coEvery {
                apiInterface.getCurrentWeather(lat = lat, long = lon, exclude = exclude)
            } returns response


            // When
            val result = weatherRepository.getWeatherData(lat, lon, exclude).single()

            // Then
            Truth.assertThat(result).isEqualTo(expectedResult)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get city name not available throw error`() {
        runTest {
            // Given
            val lat = 0.0
            val lon = 0.0
            val exclude = "exclude"
            val response = HttpException(
                Response.error<ResponseBody>(
                    500,
                    "test".toResponseBody("plain/text".toMediaTypeOrNull())
                )
            )

            coEvery {
                apiInterface.getCurrentWeather(lat = lat, long = lon, exclude = exclude)
            } throws response

            try {
                // When
                weatherRepository.getWeatherData(lat, lon, exclude).single()
            } catch (exception: Exception) {
                // Then
                Truth.assertThat(exception).isNotNull()
            }
        }
    }
}
