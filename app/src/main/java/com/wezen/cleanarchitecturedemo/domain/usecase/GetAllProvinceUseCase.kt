package com.wezen.cleanarchitecturedemo.domain.usecase

import com.wezen.cleanarchitecturedemo.data.model.DistrictEntity
import com.wezen.cleanarchitecturedemo.data.model.ProvinceEntity
import com.wezen.cleanarchitecturedemo.data.model.WardEntity
import com.wezen.cleanarchitecturedemo.domain.repository.ProvinceRepository
import javax.inject.Inject

class GetAllProvinceUseCase @Inject constructor(private val provinceRepository: ProvinceRepository) {

    suspend fun getAllProvince(): List<ProvinceEntity> {
        return provinceRepository.getAllProvince()
    }

    suspend fun getDistrictsByProvinceId(provinceId: Int): List<DistrictEntity> {
        return provinceRepository.getDistrictByProvinceId(provinceId)
    }

    suspend fun getWardsByDistrictId(districtId: Int): List<WardEntity> {
        return provinceRepository.getWardByDistrictId(districtId)
    }
}