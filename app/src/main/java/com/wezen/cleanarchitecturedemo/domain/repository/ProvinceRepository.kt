package com.wezen.cleanarchitecturedemo.domain.repository

import com.wezen.cleanarchitecturedemo.domain.model.DistrictEntity
import com.wezen.cleanarchitecturedemo.domain.model.ProvinceEntity
import com.wezen.cleanarchitecturedemo.domain.model.WardEntity

interface ProvinceRepository {
    suspend fun getAllProvince(): List<ProvinceEntity>

    suspend fun getDistrictByProvinceId(provinceId: Int): List<DistrictEntity>

    suspend fun getWardByDistrictId(districtId: Int): List<WardEntity>

}