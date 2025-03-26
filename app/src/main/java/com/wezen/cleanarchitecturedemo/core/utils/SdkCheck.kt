package com.wezen.cleanarchitecturedemo.core.utils

import android.os.Build

/** SDK 30 - R*/
fun isSdk30() = isSdkR()
fun isSdkR() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

/** SDK 33 - TIRAMISU*/
fun isSdk33() = isSdkTIRAMISU()
fun isSdkTIRAMISU() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

/** SDK 34 - UP_SIDE_DOWN_CAKE*/
fun isSdk34() = isSdkUpSideDownCake()
fun isSdkUpSideDownCake() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE