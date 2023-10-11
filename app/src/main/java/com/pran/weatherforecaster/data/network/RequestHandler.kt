package com.pran.weatherforecaster.data.network

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T : Any> handleRequestOnFlow(
    coroutineContext: CoroutineContext = Dispatchers.IO,
    requestFunc: suspend () -> T
): Flow<T> {
    return flow {
        try {
            emit(requestFunc.invoke())
        } catch (e: Exception) {
            throw e
        }
    }.flowOn(coroutineContext)
}
