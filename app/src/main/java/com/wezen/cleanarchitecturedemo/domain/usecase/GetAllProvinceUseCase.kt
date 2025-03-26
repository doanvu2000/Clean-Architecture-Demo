package com.wezen.cleanarchitecturedemo.domain.usecase

import com.wezen.cleanarchitecturedemo.data.model.ProvinceEntity
import com.wezen.cleanarchitecturedemo.domain.repository.ProvinceRepository
import javax.inject.Inject

class GetAllProvinceUseCase @Inject constructor(private val provinceRepository: ProvinceRepository) {

    suspend operator fun invoke(): List<ProvinceEntity> {
        return provinceRepository.getAllProvince()
    }
}