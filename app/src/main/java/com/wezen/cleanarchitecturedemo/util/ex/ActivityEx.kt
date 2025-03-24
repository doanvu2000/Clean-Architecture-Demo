package com.wezen.cleanarchitecturedemo.util.ex

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import com.wezen.cleanarchitecturedemo.R

fun ComponentActivity.handleBackPressed(action: () -> Unit) {
    onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            action()
        }
    })
}

fun Activity.finishWithSlide() {
    finish()
    if (isSdk34()) {
        overrideActivityTransition(
            Activity.OVERRIDE_TRANSITION_CLOSE,
            R.anim.slide_in_left,
            R.anim.slide_out_right,
            Color.TRANSPARENT
        )
    } else {
        @Suppress("DEPRECATION") overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }
}

fun Activity.finishWithSlideLTR() {
    finish()
    if (isSdk34()) {
        overrideActivityTransition(
            Activity.OVERRIDE_TRANSITION_CLOSE,
            R.anim.slide_in_right,
            R.anim.slide_out_left,
            Color.TRANSPARENT
        )
    } else {
        @Suppress("DEPRECATION") overridePendingTransition(
            R.anim.slide_in_right, R.anim.slide_out_left
        )
    }
}

fun Activity.openWithSlideLTR() {
    if (isSdk34()) {
        overrideActivityTransition(
            Activity.OVERRIDE_TRANSITION_OPEN,
            R.anim.slide_in_left,
            R.anim.slide_out_right,
            Color.TRANSPARENT
        )
    } else {
        @Suppress("DEPRECATION") overridePendingTransition(
            R.anim.slide_in_left, R.anim.slide_out_right
        )
    }
}

fun Activity.openWithSlide() {
    if (isSdk34()) {
        overrideActivityTransition(
            Activity.OVERRIDE_TRANSITION_OPEN,
            R.anim.slide_in_right,
            R.anim.slide_out_left,
            Color.TRANSPARENT
        )
    } else {
        @Suppress("DEPRECATION") overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
    }
}

fun Context.intToDp(dp: Int): Int {
    return dp * resources.displayMetrics.density.toInt()
}

fun Int.dp(context: Context): Int {
    return context.intToDp(this)
}

fun Context.floatToDp(dp: Float): Float {
    return dp * resources.displayMetrics.density
}

fun Float.dp(context: Context): Int {
    return context.floatToDp(this).toInt()
}

@SuppressLint("UnspecifiedRegisterReceiverFlag")
fun Activity.registerReceiverBroadcast(receiver: BroadcastReceiver, intentFilter: IntentFilter) {
    if (isSdk33()) {
        registerReceiver(
            receiver,
            intentFilter,
            Context.RECEIVER_EXPORTED
        )
    } else {
        registerReceiver(receiver, intentFilter)
    }
}