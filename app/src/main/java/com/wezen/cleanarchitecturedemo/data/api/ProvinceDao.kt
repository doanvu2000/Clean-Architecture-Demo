package com.wezen.cleanarchitecturedemo.data.api

import androidx.room.Dao
import androidx.room.Query
import com.wezen.cleanarchitecturedemo.domain.model.DistrictEntity
import com.wezen.cleanarchitecturedemo.domain.model.ProvinceEntity
import com.wezen.cleanarchitecturedemo.domain.model.WardEntity

@Dao
interface ProvinceDao {
    @Query("SELECT * FROM provinces")
    suspend fun getAllProvince(): List<ProvinceEntity>

    @Query("SELECT * FROM districts WHERE parent_code = :provinceId")
    suspend fun getDistrictsByProvinceId(provinceId: Int): List<DistrictEntity>

    @Query("SELECT * FROM wards WHERE parent_code = :districtId")
    suspend fun getWardsByDistrictId(districtId: Int): List<WardEntity>
}