package com.wezen.cleanarchitecturedemo.presentation.ui.main.district

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.wezen.cleanarchitecturedemo.core.common.BaseFragment
import com.wezen.cleanarchitecturedemo.core.utils.setLinearLayoutManager
import com.wezen.cleanarchitecturedemo.databinding.FragmentDistrictBinding
import com.wezen.cleanarchitecturedemo.presentation.viewmodel.ProvinceViewModel
import com.wezen.cleanarchitecturedemo.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DistrictFragment : BaseFragment<FragmentDistrictBinding>() {
    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDistrictBinding {
        return FragmentDistrictBinding.inflate(inflater, container, false)
    }

    private val viewModel: ProvinceViewModel by activityViewModels()

    private val districtAdapter by lazy {
        DistrictAdapter()
    }

    override fun initView() {
        binding.rcvDistrict.setLinearLayoutManager(requireContext(), districtAdapter)
    }

    override fun initData() {
        viewModel.districtsByProvinceId.observe(viewLifecycleOwner) { districts ->
            districtAdapter.setDataList(districts)
        }
    }

    override fun initListener() {
        districtAdapter.setOnClickItem { item, position ->
            item?.let {
                viewModel.updateResult(item.pathWithType)
                viewModel.getWardsByDistrictId(item.code)

                (requireActivity() as MainActivity).nextPage()
            }
        }
    }
}