package com.wezen.cleanarchitecturedemo.domain.usecase

import com.wezen.cleanarchitecturedemo.data.model.DistrictEntity
import com.wezen.cleanarchitecturedemo.domain.repository.ProvinceRepository
import javax.inject.Inject

class GetDistrictsUseCase @Inject constructor(private val provinceRepository: ProvinceRepository) {
    suspend operator fun invoke(provinceId: Int): List<DistrictEntity> {
        return provinceRepository.getDistrictByProvinceId(provinceId)
    }
}