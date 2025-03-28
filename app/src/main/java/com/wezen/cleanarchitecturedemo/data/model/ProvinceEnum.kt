package com.wezen.cleanarchitecturedemo.data.model

enum class ProvinceEnum {
    Province,
    District,
    Ward;

    companion object {
        fun getNumberType() = entries.size
        fun getType(position: Int) = entries.getOrNull(position) ?: Province
    }
}