package com.wezen.cleanarchitecturedemo.util.ex
import android.content.res.Resources
import android.util.TypedValue
import com.wezen.cleanarchitecturedemo.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun isDebugMode() = BuildConfig.DEBUG

fun tryCatch(action: () -> Unit) {
    try {
        action
    }catch (e: Exception){
        e.printStackTrace()
    }
}

fun Int.dpToPx(): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics
    ).toInt()
}

/**
 * Preventing click multiple view
 * */
var readyClickStatic = true
fun delayClickStatic(timeDelay: Long = 200L) {
    readyClickStatic = false
    CoroutineScope(Dispatchers.IO).launch {
        delay(timeDelay)
        readyClickStatic = true
    }
}

fun clickStatic(action: () -> Unit) {
    if (readyClickStatic) {
        delayClickStatic()
        action()
    }
}