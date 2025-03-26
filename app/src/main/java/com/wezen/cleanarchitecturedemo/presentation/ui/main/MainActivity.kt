package com.wezen.cleanarchitecturedemo.presentation.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.wezen.cleanarchitecturedemo.R
import com.wezen.cleanarchitecturedemo.databinding.ActivityMainBinding
import com.wezen.cleanarchitecturedemo.data.model.ProvinceEnum
import com.wezen.cleanarchitecturedemo.presentation.viewmodel.ProvinceViewModel
import com.wezen.cleanarchitecturedemo.core.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    private val viewModel by viewModels<ProvinceViewModel>()

    private val callbackViewPager = object : ViewPager2.OnPageChangeCallback() {
        @SuppressLint("SetTextI18n")
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            when (ProvinceEnum.getType(position)) {
                ProvinceEnum.Province -> {
                    binding.tvTitle.text = "Choose " + getString(R.string.txt_province)
                }

                ProvinceEnum.District -> {
                    binding.tvTitle.text = "Choose " + getString(R.string.txt_district)
                }

                ProvinceEnum.Ward -> {
                    binding.tvTitle.text = "Choose " + getString(R.string.txt_ward)
                }
            }
        }
    }

    private val provinceAdapter by lazy {
        ProvinceViewPagerAdapter(supportFragmentManager, lifecycle)
    }

    override fun onResume() {
        super.onResume()
        binding.provinceViewPager.registerOnPageChangeCallback(callbackViewPager)
    }

    override fun onPause() {
        super.onPause()
        binding.provinceViewPager.unregisterOnPageChangeCallback(callbackViewPager)
    }

    override fun initView() {
        binding.provinceViewPager.apply {
            adapter = provinceAdapter
        }
    }

    override fun initData() {
        lifecycleScope.launch {
            viewModel.result.observe(this@MainActivity) { result ->
                binding.tvResult.text = result
            }
        }
    }

    override fun initListener() {

    }

    fun nextPage() {
        val index = binding.provinceViewPager.currentItem
        binding.provinceViewPager.currentItem = index + 1
    }
}