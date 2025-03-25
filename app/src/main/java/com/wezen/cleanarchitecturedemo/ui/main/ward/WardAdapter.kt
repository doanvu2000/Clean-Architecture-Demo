package com.wezen.cleanarchitecturedemo.ui.main.ward

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wezen.cleanarchitecturedemo.base.BaseAdapterRecyclerView
import com.wezen.cleanarchitecturedemo.databinding.ItemProvinceBinding
import com.wezen.cleanarchitecturedemo.domain.model.WardEntity

class WardAdapter : BaseAdapterRecyclerView<WardEntity, ItemProvinceBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemProvinceBinding {
        return ItemProvinceBinding.inflate(inflater, parent, false)
    }

    override fun bindData(
        binding: ItemProvinceBinding,
        item: WardEntity,
        position: Int
    ) {
        binding.tvContent.text = item.nameWithType
    }
}