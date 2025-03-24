package com.wezen.cleanarchitecturedemo.ui.main

import android.view.LayoutInflater
import com.wezen.cleanarchitecturedemo.databinding.ActivityMainBinding
import com.wezen.dmv.base.screen.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {

    }
}