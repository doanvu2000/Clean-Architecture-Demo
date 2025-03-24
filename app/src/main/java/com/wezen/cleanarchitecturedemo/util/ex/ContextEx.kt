package com.wezen.cleanarchitecturedemo.util.ex

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.wezen.cleanarchitecturedemo.R
import java.io.IOException
import java.io.InputStream

fun Context.showToast(msg: String, isDurationLong: Boolean = false) {
    val duration = if (isDurationLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    Toast.makeText(this, msg, duration).show()
}

fun Context.openActivity(pClass: Class<out Activity>, bundle: Bundle?) {
    val intent = Intent(this, pClass)
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    startActivity(intent)
}

fun Context.openActivity(
    pClass: Class<out Activity>,
    isFinish: Boolean = false,
    isAnimation: Boolean = true,
    bundle: Bundle? = null
) {
    openActivity(pClass, bundle)
    if (isAnimation) {
        (this as Activity).openWithSlide()
    }
    if (isFinish) {
        (this as Activity).finish()
    }
}

fun Context.openActivity(
    pClass: Class<out Activity>,
    isFinish: Boolean = false,
    isAnimation: Boolean = false,
    bundle: Bundle?,
    isLTR: Boolean = false
) {
    openActivity(pClass, bundle)
    if (isAnimation) {
        if (isLTR) {
            (this as Activity).openWithSlideLTR()
        } else {
            (this as Activity).openWithSlide()
        }
    }
    if (isFinish) {
        (this as Activity).finish()
    }
}

fun Context.inflateLayout(layoutResource: Int, parent: ViewGroup): View {
    return LayoutInflater.from(this).inflate(layoutResource, parent, false)
}

fun Context.getColorById(colorSource: Int): Int {
    return ContextCompat.getColor(this, colorSource)
}

fun Context.shareApp() {
    shareText(getLinkApp())
}

fun Context.getLinkApp() = "https://play.google.com/store/apps/details?id=$packageName"

fun Context.shareText(value: String) {
    val sendIntent = Intent()
    sendIntent.apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, value)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Context.sendEmail(toEmail: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    val data =
        ("mailto:" + toEmail + "?subject=" + "Feedback for ${getString(R.string.app_name)}" + "&body=" + "").toUri()
    intent.data = data
    try {
        startActivity(intent)
    } catch (ex: Exception) {
        Toast.makeText(
            this, "Not have email app to send email!", Toast.LENGTH_SHORT
        ).show()
        ex.printStackTrace()
    }
}


fun Context.navigateToMarket() {
    val market = "market://details?id="
    val webPlayStore = "https://play.google.com/store/apps/details?id="
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW, (market + packageName).toUri()
                //market://details?id=<package_name>
            )
        )
    } catch (e: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW, (webPlayStore + packageName).toUri()
                //https://play.google.com/store/apps/details?id=<package_name>
            )
        )
    }
}


fun Context.navigateToProfileMarket() {
    val publishName = "7717501896293852484"
    val market = "market://store/apps/dev?id=$publishName"
    val webPlayStore = "https://play.google.com/store/apps/dev?id=$publishName"
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW, market.toUri()
            )
        )
    } catch (e: ActivityNotFoundException) {
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW, webPlayStore.toUri()
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun Context.isInternetAvailable(): Boolean {
    var result = false
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
    result = when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }

    return result
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.loadJsonFromAsset(path: String): String? {
    var json: String? = null
    try {
        val ios: InputStream = assets.open(path)
        val size = ios.available()
        val buffer = ByteArray(size)
        ios.read(buffer)
        ios.close()
        json = String(buffer, Charsets.UTF_8)
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return json
}