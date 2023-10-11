package com.pran.weatherforecaster.di.component

import android.app.Application
import com.pran.weatherforecaster.WeatherApp
import com.pran.weatherforecaster.di.module.ActivityBindingModule
import com.pran.weatherforecaster.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: WeatherApp)

}

