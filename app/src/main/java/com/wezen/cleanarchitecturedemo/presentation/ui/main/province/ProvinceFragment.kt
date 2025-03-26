package com.wezen.cleanarchitecturedemo.presentation.ui.main.province

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.wezen.cleanarchitecturedemo.core.common.BaseFragment
import com.wezen.cleanarchitecturedemo.core.utils.ex.setLinearLayoutManager
import com.wezen.cleanarchitecturedemo.databinding.FragmentProvinceBinding
import com.wezen.cleanarchitecturedemo.presentation.viewmodel.ProvinceViewModel
import com.wezen.cleanarchitecturedemo.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProvinceFragment : BaseFragment<FragmentProvinceBinding>() {
    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProvinceBinding {
        return FragmentProvinceBinding.inflate(inflater, container, false)
    }

    private val provinceAdapter by lazy {
        ProvinceAdapter()
    }

    private val viewModel: ProvinceViewModel by activityViewModels()

    override fun initView() {
        binding.rcvProvince.setLinearLayoutManager(requireContext(), provinceAdapter)
    }

    override fun initData() {
        viewModel.getAllProvince()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allProvince.observe(viewLifecycleOwner) { provinces ->
                provinceAdapter.setDataList(provinces)
            }
        }
    }

    override fun initListener() {
        provinceAdapter.setOnClickItem { item, position ->
            item?.let {
                viewModel.updateResult(item.name)
                viewModel.getDistrictsByProvinceId(item.code)
                (requireActivity() as MainActivity).nextPage()
            }
        }
    }
}