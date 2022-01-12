package com.sahar.composecleanarchitecture.utils.ext

import timber.log.Timber

fun Any.logError(message: String) {
    Timber.e("${javaClass.simpleName} ********************* $message *********************")
}

fun Any.logInfo(message: String) {
    Timber.i("${javaClass.simpleName} ~~~~~~~~~~~~~~~~~~~~~ $message ~~~~~~~~~~~~~~~~~~~~~")
}

fun Any.logDebug(message: String){
    Timber.d("${javaClass.simpleName} -------------------- $message --------------------")
}



