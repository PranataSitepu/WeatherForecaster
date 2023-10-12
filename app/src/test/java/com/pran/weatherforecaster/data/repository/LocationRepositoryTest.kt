package com.pran.weatherforecaster.data.repository

import com.google.common.truth.Truth
import com.pran.weatherforecaster.data.datasource.remote.LocalLocationDataSource
import com.pran.weatherforecaster.data.datasource.remote.RemoteLocationDataSource
import com.pran.weatherforecaster.data.db.LocationDao
import com.pran.weatherforecaster.data.model.local.FavoriteCityEntity
import com.pran.weatherforecaster.data.model.remote.CityListDto
import com.pran.weatherforecaster.data.model.remote.CityNameDto
import com.pran.weatherforecaster.data.network.ApiInterface
import com.pran.weatherforecaster.domain.model.City
import com.pran.weatherforecaster.domain.repository.LocationRepository
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
class LocationRepositoryTest {

    @MockK
    private lateinit var apiInterface: ApiInterface

    @MockK
    private lateinit var locationDao: LocationDao

    private lateinit var locationRepository: LocationRepository
    private lateinit var remoteDataSource: RemoteLocationDataSource
    private lateinit var localDataSource: LocalLocationDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        remoteDataSource = RemoteLocationDataSource(apiInterface)
        localDataSource = LocalLocationDataSource(locationDao)
        locationRepository = DefaultLocationRepository(remoteDataSource, localDataSource)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get city name available return name`() {
        runTest {
            // Given
            val lat = 0.0
            val lon = 0.0
            val response = listOf(CityNameDto(name = "Jakarta"))

            coEvery {
                apiInterface.getCityName(lat, lon)
            } returns response


            // When
            val result = locationRepository.getCityName(lat, lon).single()

            // Then
            Truth.assertThat(result).isEqualTo("Jakarta")
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get city name not available throw error`() {
        runTest {
            // Given
            val lat = 0.0
            val lon = 0.0
            val response = HttpException(
                Response.error<ResponseBody>(
                    500,
                    "test".toResponseBody("plain/text".toMediaTypeOrNull())
                )
            )

            coEvery {
                apiInterface.getCityName(lat, lon)
            } throws response

            try {
                // When
                locationRepository.getCityName(lat, lon)
            } catch (exception: Exception) {
                // Then
                Truth.assertThat(exception).isNotNull()
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get city list available return list`() {
        runTest {
            // Given
            val query = "correct"
            val response = CityListDto(
                results = listOf(
                    CityListDto.CityDto(
                        name = "Jakarta",
                        latitude = 0.0,
                        longitude = 0.0
                    )
                )
            )
            val expectedResult = listOf(City(name = "Jakarta", latitude = 0.0, longitude = 0.0))

            coEvery {
                apiInterface.getCityList(query = query)
            } returns response


            // When
            val result = locationRepository.getCityList(query).single()

            // Then
            Truth.assertThat(result).isEqualTo(expectedResult)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get city list empty return empty`() {
        runTest {
            // Given
            val query = "empty"
            val response = CityListDto(results = emptyList())
            val expectedResult = emptyList<City>()

            coEvery {
                apiInterface.getCityList(query = query)
            } returns response


            // When
            val result = locationRepository.getCityList(query).single()

            // Then
            Truth.assertThat(result).isEqualTo(expectedResult)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get city list not available throw error`() {
        runTest {
            // Given
            val query = "error"
            val response = HttpException(
                Response.error<ResponseBody>(
                    500,
                    "test".toResponseBody("plain/text".toMediaTypeOrNull())
                )
            )

            coEvery {
                apiInterface.getCityList(query = query)
            } throws response

            try {
                // When
                locationRepository.getCityList(query = query)
            } catch (exception: Exception) {
                // Then
                Truth.assertThat(exception).isNotNull()
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `load favorite city available return list`() {
        runTest {
            // Given
            val response =
                listOf(FavoriteCityEntity(cityName = "Jakarta", latitude = 0.0, longitude = 0.0))
            val expectedResult = listOf(City(name = "Jakarta", latitude = 0.0, longitude = 0.0))

            coEvery {
                locationDao.getAll()
            } returns response


            // When
            val result = locationRepository.loadFavoriteCity()

            // Then
            Truth.assertThat(result).isEqualTo(expectedResult)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `load favorite city empty return empty`() {
        runTest {
            // Given
            val response = emptyList<FavoriteCityEntity>()
            val expectedResult = emptyList<City>()

            coEvery {
                locationDao.getAll()
            } returns response


            // When
            val result = locationRepository.loadFavoriteCity()

            // Then
            Truth.assertThat(result).isEqualTo(expectedResult)
        }
    }
}
