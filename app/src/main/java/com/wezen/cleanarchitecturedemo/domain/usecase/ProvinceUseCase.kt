package com.wezen.cleanarchitecturedemo.domain.usecase

import javax.inject.Inject

class ProvinceUseCase @Inject constructor(
    val getProvinces: GetAllProvinceUseCase,
    val getDistrictsByProvinceId: GetDistrictsUseCase,
    val getWardsByDistrictId: GetWardsUseCase
)