package com.pran.weatherforecaster.di.module

import com.pran.weatherforecaster.data.datasource.LocationDataSource
import com.pran.weatherforecaster.data.datasource.WeatherDataSource
import com.pran.weatherforecaster.data.datasource.remote.LocalLocationDataSource
import com.pran.weatherforecaster.data.datasource.remote.RemoteLocationDataSource
import com.pran.weatherforecaster.data.datasource.remote.RemoteWeatherDataSource
import com.pran.weatherforecaster.data.db.LocationDao
import com.pran.weatherforecaster.data.network.ApiInterface
import com.pran.weatherforecaster.data.repository.DefaultLocationRepository
import com.pran.weatherforecaster.data.repository.DefaultWeatherRepository
import com.pran.weatherforecaster.data.repository.LocationRepository
import com.pran.weatherforecaster.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    @Singleton
    fun provideRemoteWeatherDataSource(apiInterface: ApiInterface): WeatherDataSource.Remote {
        return RemoteWeatherDataSource(apiInterface)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        remoteDataSource: RemoteWeatherDataSource
    ): WeatherRepository {
        return DefaultWeatherRepository(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideRemoteLocationDataSource(apiInterface: ApiInterface): LocationDataSource.Remote {
        return RemoteLocationDataSource(apiInterface)
    }

    @Provides
    @Singleton
    fun provideLocalLocationDataSource(locationDao: LocationDao): LocationDataSource.Local {
        return LocalLocationDataSource(locationDao)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(
        remoteDataSource: RemoteLocationDataSource,
        localDataSource: LocalLocationDataSource
    ): LocationRepository {
        return DefaultLocationRepository(remoteDataSource, localDataSource)
    }
}
