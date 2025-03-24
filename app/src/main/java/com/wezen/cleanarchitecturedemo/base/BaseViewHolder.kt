package com.wezen.cleanarchitecturedemo.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseViewHolder<VB : ViewBinding>(val binding: VB) :
    RecyclerView.ViewHolder(binding.root)