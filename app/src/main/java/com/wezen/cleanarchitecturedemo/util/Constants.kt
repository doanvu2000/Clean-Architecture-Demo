package com.wezen.cleanarchitecturedemo.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object Constants {
    const val TAG = "JinLog"

    var isRequestPermission = false
}

fun delayResetFlagPermission() {
    CoroutineScope(Dispatchers.IO).launch {
        delay(500)
        Constants.isRequestPermission = false
    }
}