package com.pran.weatherforecaster.domain.model

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val error: Throwable) : Resource<T>()
    class Loading<T> : Resource<T>()
    class Initial<T> : Resource<T>()
}
