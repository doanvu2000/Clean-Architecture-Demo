package com.wezen.cleanarchitecturedemo.ui.main.ward

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.wezen.cleanarchitecturedemo.base.BaseFragment
import com.wezen.cleanarchitecturedemo.core.utils.ex.setLinearLayoutManager
import com.wezen.cleanarchitecturedemo.databinding.FragmentWardBinding
import com.wezen.cleanarchitecturedemo.domain.model.ProvinceEnum
import com.wezen.cleanarchitecturedemo.presentation.viewmodel.ProvinceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WardFragment : BaseFragment<FragmentWardBinding>() {
    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWardBinding {
        return FragmentWardBinding.inflate(inflater, container, false)
    }

    private val viewModel: ProvinceViewModel by activityViewModels()

    private val wardAdapter by lazy {
        WardAdapter()
    }

    override fun initView() {
        binding.rcvWard.setLinearLayoutManager(requireContext(), wardAdapter)
    }

    override fun initData() {
        viewModel.wardsByDistrictId.observe(viewLifecycleOwner) { wards ->
            wardAdapter.setDataList(wards)
        }
    }

    override fun initListener() {
        wardAdapter.setOnClickItem { item, position ->
            item?.let {
                viewModel.updateResult(item.pathWithType)
            }
        }
    }
}