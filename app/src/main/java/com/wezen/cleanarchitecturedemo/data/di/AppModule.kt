package com.wezen.cleanarchitecturedemo.data.di

import android.content.Context
import androidx.room.Room
import com.wezen.cleanarchitecturedemo.data.api.ProvinceDao
import com.wezen.cleanarchitecturedemo.data.api.ProvinceDatabase
import com.wezen.cleanarchitecturedemo.data.repository.ProvinceRepositoryImpl
import com.wezen.cleanarchitecturedemo.domain.repository.ProvinceRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideProvinceDatabase(@ApplicationContext context: Context): ProvinceDatabase {
        return Room.databaseBuilder(context, ProvinceDatabase::class.java, "vn_province.db")
            .createFromAsset("vietnam_province.db")
            .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideProvinceDao(provinceDatabase: ProvinceDatabase): ProvinceDao {
        return provinceDatabase.provinceDao()
    }

//    @Provides
//    @Singleton
//    fun provideProvinceRepository(provinceDao: ProvinceDao): ProvinceRepository {
//        return ProvinceRepositoryImpl(provinceDao)
//    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindProvinceRepository(provinceRepositoryImpl: ProvinceRepositoryImpl): ProvinceRepository

}