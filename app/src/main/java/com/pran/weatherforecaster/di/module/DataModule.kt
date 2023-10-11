package com.pran.weatherforecaster.di.module

import com.pran.weatherforecaster.data.datasource.LocalWeatherDataSource
import com.pran.weatherforecaster.data.datasource.RemoteWeatherDataSource
import com.pran.weatherforecaster.data.datasource.WeatherDataSource
import com.pran.weatherforecaster.data.network.ApiInterface
import com.pran.weatherforecaster.data.repository.DefaultWeatherRepository
import com.pran.weatherforecaster.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRemoteWeatherDataSource(apiInterface: ApiInterface): WeatherDataSource.Remote {
        return RemoteWeatherDataSource(apiInterface)
    }

    @Provides
    @Singleton
    fun provideLocalWeatherDataSource(): WeatherDataSource.Local {
        return LocalWeatherDataSource()
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        remoteDataSource: RemoteWeatherDataSource,
        localDataSource: LocalWeatherDataSource
    ): WeatherRepository {
        return DefaultWeatherRepository(remoteDataSource, localDataSource)
    }
}
