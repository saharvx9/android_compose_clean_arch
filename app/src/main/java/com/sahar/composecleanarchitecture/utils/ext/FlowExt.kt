package com.sahar.composecleanarchitecture.utils.ext

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> {
    var job: Job = Job().apply { complete() }

    return onCompletion { job.cancel() }.run {
        flow {
            coroutineScope {
                collect { value ->
                    if (!job.isActive) {
                        emit(value)
                        job = launch { delay(windowDuration) }
                    }
                }
            }
        }
    }
}

// ~~~~~~~~~~~~~~ Coroutines Ext ~~~~~~~~~~~~~~
fun <T> CoroutineScope.asyncIOThread(block: suspend CoroutineScope.() -> T): Deferred<T> = async(
    Dispatchers.IO) { block() }
fun <T> CoroutineScope.asyncDefaultThread(block: suspend CoroutineScope.() -> T): Deferred<T> = async(
    Dispatchers.Default) { block() }

// ~~~~~~~~~~~~~~ Flow Ext ~~~~~~~~~~~~~~
fun <T> Flow<T>.flowOnMainThread(): Flow<T> = flowOn(Dispatchers.Main)
fun <T> Flow<T>.flowOnIOThread(): Flow<T> = flowOn(Dispatchers.IO)
fun <T> Flow<T>.flowOnDefaultThread(): Flow<T> = flowOn(Dispatchers.Default)