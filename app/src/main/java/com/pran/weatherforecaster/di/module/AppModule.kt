package com.pran.weatherforecaster.di.module

import android.app.Application
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        context: Context,
    ): ChuckerInterceptor =
        ChuckerInterceptor.Builder(context = context)
            .collector(
                collector = ChuckerCollector(
                    context = context,
                    showNotification = true,
                    retentionPeriod = RetentionManager.Period.ONE_HOUR,
                )
            )
            .maxContentLength(length = 120000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(enable = true)
            .build()
}
