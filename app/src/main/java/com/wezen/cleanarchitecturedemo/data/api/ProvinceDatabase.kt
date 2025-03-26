package com.wezen.cleanarchitecturedemo.data.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wezen.cleanarchitecturedemo.data.model.DistrictEntity
import com.wezen.cleanarchitecturedemo.data.model.ProvinceEntity
import com.wezen.cleanarchitecturedemo.data.model.WardEntity

@Database(
    entities = [ProvinceEntity::class, DistrictEntity::class, WardEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProvinceDatabase : RoomDatabase() {
    abstract fun provinceDao(): ProvinceDao
}