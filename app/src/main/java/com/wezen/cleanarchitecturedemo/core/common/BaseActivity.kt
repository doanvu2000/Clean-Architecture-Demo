package com.wezen.dmv.base.screen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.wezen.cleanarchitecturedemo.presentation.MyApplication
import com.wezen.cleanarchitecturedemo.core.utils.AppLogger
import com.wezen.cleanarchitecturedemo.core.utils.Constants
import com.wezen.cleanarchitecturedemo.core.utils.SharePrefUtils
import com.wezen.cleanarchitecturedemo.core.utils.delayResetFlagPermission
import com.wezen.cleanarchitecturedemo.core.utils.ex.clickAnimation
import com.wezen.cleanarchitecturedemo.core.utils.ex.finishWithSlide
import com.wezen.cleanarchitecturedemo.core.utils.ex.handleBackPressed
import com.wezen.cleanarchitecturedemo.core.utils.ex.isInternetAvailable
import com.wezen.cleanarchitecturedemo.core.utils.ex.registerReceiverBroadcast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    //region variable
    companion object {
        const val TAG = Constants.TAG
        const val TIME_DELAY_CLICK = 200L
        const val ACTION_NETWORK_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE"
    }

    lateinit var binding: VB
    private var isAvailableClick = true

    val myApplication by lazy {
        application as MyApplication
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding(layoutInflater)
        setContentView(binding.root)
        AppLogger.e(TAG, "---------------------SCREEN_APP: $localClassName---------------------")
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        SharePrefUtils.init(this)
//        screenWidth = getScreenWidth()
//        screenHeight = getScreenHeight()
        checkInitRemoteConfig()
        initView()
        initData()
        initListener()
        handleBackPressed {
            onBack()
        }
    }

    open fun onBack() {
        finishWithSlide()
//        if (supportFragmentManager.backStackEntryCount > 0) {
//            onBackPressedDispatcher.onBackPressed()
//        } else {
//            finishWithSlide()
//        }
    }

    /**override it and inflate your view binding, demo in MainActivity*/
    abstract fun inflateViewBinding(inflater: LayoutInflater): VB
    abstract fun initView()
    abstract fun initData()
    abstract fun initListener()

    private fun delayClick() {
        lifecycleScope.launch(Dispatchers.IO) {
            isAvailableClick = false
            delay(TIME_DELAY_CLICK)
            isAvailableClick = true
        }
    }

    fun View.clickSafe(isAnimationClick: Boolean = false, action: () -> Unit) {
        this.setOnClickListener {
            if (isAvailableClick) {
//                if (!Constants.isDirectToAds) {
//                }
                if (isAnimationClick) {
                    clickAnimation()
                }
                action()
                delayClick()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (checkInternet()) {
            registerReceiverBroadcast(networkReceiver, IntentFilter(ACTION_NETWORK_CHANGE))
        }
    }

    override fun onPause() {
        super.onPause()
        if (checkInternet()) {
            unregisterReceiver(networkReceiver)
        }
    }

    private fun checkInitRemoteConfig() {
//        try {
//            if (!RemoteConfigUtil.isInitialize(this)) {
//                RemoteConfigUtil.init(this)
//            }
//        } catch (e: Exception) {
//            AppLogger().e(TAG, "checkInitRemoteConfig: error when try init RemoteConfig")
//        }
    }

//    private val dialogNoInternet by lazy {
//        DialogNoInternet(this)
//    }

    /**checking internet*/
    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == ACTION_NETWORK_CHANGE) {
//                if (RemoteConfigUtil.internetChecking) {
//                if (!RemoteConfigUtil.internetChecking) {
//                    return
//                }
                if (!checkInternet()) {
                    return
                }
                if (isInternetAvailable()) {
//                    dialogNoInternet.hide()
                } else {
//                    dialogNoInternet.show(onClickSetting = {
//                        try {
//                            Constants.isRequestPermission = true
//                            launcherSettingWifi.launch(Intent(Settings.ACTION_WIFI_SETTINGS))
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                        }
//                    })
                }
            }
        }
    }

    private val launcherSettingWifi =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            delayResetFlagPermission()
        }

    open fun checkInternet() = true

    fun launchAction(dispatcher: CoroutineContext, action: () -> Unit) {
        try {
            lifecycleScope.launch(dispatcher) {
                action()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun launchCoroutine(
        dispatcher: CoroutineContext, blockCoroutine: suspend CoroutineScope.() -> Unit
    ) {
        try {
            lifecycleScope.launch(dispatcher) {
                blockCoroutine()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun launchCoroutineMain(blockCoroutine: suspend CoroutineScope.() -> Unit) {
        launchCoroutine(Dispatchers.Main) {
            blockCoroutine()
        }
    }

    fun launchCoroutineIO(blockCoroutine: suspend CoroutineScope.() -> Unit) {
        launchCoroutine(Dispatchers.IO) {
            blockCoroutine()
        }
    }

    fun delayToAction(delayTime: Long = 200L, action: () -> Unit) {
        launchCoroutineIO {
            delay(delayTime)
            launchCoroutineMain {
                action()
            }
        }
    }

    /**
     * Ignore margin bottom when action compat of system, such as: share
     * */
    protected fun adjustInsetsForBottomNavigation(viewBottom: View) {
        ViewCompat.setOnApplyWindowInsetsListener(viewBottom) { view, insets ->
            try {
                val params = view.layoutParams as ViewGroup.MarginLayoutParams
                val displayCutout =
                    insets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars())
                params.topMargin = displayCutout.top
                params.leftMargin = displayCutout.left
                params.rightMargin = displayCutout.right
                params.bottomMargin = displayCutout.bottom
                view.layoutParams = params
            } catch (e: Exception) {
                e.printStackTrace()
            }
            insets
        }
    }


    override fun onDestroy() {
//        stopCountDownLoadNative()
        super.onDestroy()
    }

    private var screenWidth = 0
    var screenHeight = 0
}