package com.wezen.cleanarchitecturedemo.domain.usecase

import com.wezen.cleanarchitecturedemo.data.model.WardEntity
import com.wezen.cleanarchitecturedemo.domain.repository.ProvinceRepository
import javax.inject.Inject

class GetWardsUseCase @Inject constructor(private val provinceRepository: ProvinceRepository) {
    suspend operator fun invoke(districtId: Int): List<WardEntity> {
        return provinceRepository.getWardByDistrictId(districtId)
    }

}