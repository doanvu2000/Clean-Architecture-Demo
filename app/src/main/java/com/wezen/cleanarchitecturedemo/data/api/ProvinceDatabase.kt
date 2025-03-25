package com.wezen.cleanarchitecturedemo.data.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wezen.cleanarchitecturedemo.domain.model.DistrictEntity
import com.wezen.cleanarchitecturedemo.domain.model.ProvinceEntity
import com.wezen.cleanarchitecturedemo.domain.model.WardEntity

@Database(
    entities = [ProvinceEntity::class, DistrictEntity::class, WardEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProvinceDatabase : RoomDatabase() {
    abstract fun provinceDao(): ProvinceDao
}