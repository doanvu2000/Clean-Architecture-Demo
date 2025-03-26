package com.wezen.cleanarchitecturedemo.data.repository

import com.wezen.cleanarchitecturedemo.data.api.ProvinceDao
import com.wezen.cleanarchitecturedemo.data.model.DistrictEntity
import com.wezen.cleanarchitecturedemo.data.model.ProvinceEntity
import com.wezen.cleanarchitecturedemo.data.model.WardEntity
import com.wezen.cleanarchitecturedemo.domain.repository.ProvinceRepository
import javax.inject.Inject

class ProvinceRepositoryImpl @Inject constructor(private val provinceDao: ProvinceDao) :
    ProvinceRepository {

    override suspend fun getAllProvince(): List<ProvinceEntity> {
        return provinceDao.getAllProvince()
    }

    override suspend fun getDistrictByProvinceId(provinceId: Int): List<DistrictEntity> {
        return provinceDao.getDistrictsByProvinceId(provinceId)
    }

    override suspend fun getWardByDistrictId(districtId: Int): List<WardEntity> {
        return provinceDao.getWardsByDistrictId(districtId)
    }
}