package com.wezen.cleanarchitecturedemo.domain.usecase

import javax.inject.Inject

class ProvinceUseCase @Inject constructor(
    val getProvinceUseCase: GetAllProvinceUseCase,
    val getDistrictsUseCase: GetDistrictsUseCase,
    val getWardsUseCase: GetWardsUseCase
)