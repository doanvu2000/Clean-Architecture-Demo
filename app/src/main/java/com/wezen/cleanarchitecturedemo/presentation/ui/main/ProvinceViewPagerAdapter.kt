package com.wezen.cleanarchitecturedemo.presentation.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wezen.cleanarchitecturedemo.data.model.ProvinceEnum
import com.wezen.cleanarchitecturedemo.presentation.ui.main.district.DistrictFragment
import com.wezen.cleanarchitecturedemo.presentation.ui.main.province.ProvinceFragment
import com.wezen.cleanarchitecturedemo.presentation.ui.main.ward.WardFragment

class ProvinceViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments = mutableMapOf<Int, Fragment?>()

    init {
        repeat(ProvinceEnum.getNumberType()) {
            fragments[it] = null
        }
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position] ?: run {
            when (ProvinceEnum.getType(position)) {
                ProvinceEnum.Province -> {
                    fragments[position] = ProvinceFragment()
                }

                ProvinceEnum.District -> {
                    fragments[position] = DistrictFragment()
                }

                ProvinceEnum.Ward -> {
                    fragments[position] = WardFragment()
                }
            }

            fragments[position]!!
        }
    }

    override fun getItemCount(): Int {
        return ProvinceEnum.getNumberType()
    }
}