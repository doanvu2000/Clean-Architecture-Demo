package com.wezen.cleanarchitecturedemo.presentation.ui.main.province

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wezen.cleanarchitecturedemo.core.common.BaseAdapterRecyclerView
import com.wezen.cleanarchitecturedemo.databinding.ItemProvinceBinding
import com.wezen.cleanarchitecturedemo.data.model.ProvinceEntity

class ProvinceAdapter : BaseAdapterRecyclerView<ProvinceEntity, ItemProvinceBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemProvinceBinding {
        return ItemProvinceBinding.inflate(inflater, parent, false)
    }

    override fun bindData(
        binding: ItemProvinceBinding,
        item: ProvinceEntity,
        position: Int
    ) {
        binding.tvContent.text = item.name
    }
}