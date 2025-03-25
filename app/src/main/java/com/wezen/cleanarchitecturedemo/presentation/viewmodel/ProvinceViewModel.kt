package com.wezen.cleanarchitecturedemo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wezen.cleanarchitecturedemo.core.utils.AppLogger
import com.wezen.cleanarchitecturedemo.core.utils.Constants
import com.wezen.cleanarchitecturedemo.domain.model.DistrictEntity
import com.wezen.cleanarchitecturedemo.domain.model.ProvinceEntity
import com.wezen.cleanarchitecturedemo.domain.model.WardEntity
import com.wezen.cleanarchitecturedemo.domain.usecase.GetAllProvinceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProvinceViewModel @Inject constructor(private val provinceUseCase: GetAllProvinceUseCase) :
    ViewModel() {
    companion object {
        const val TAG = Constants.TAG
    }

    private val _allProvince = MutableLiveData<List<ProvinceEntity>>()
    val allProvince: LiveData<List<ProvinceEntity>> = _allProvince

    private val _districtsByProvinceId = MutableLiveData<List<DistrictEntity>>()
    val districtsByProvinceId: LiveData<List<DistrictEntity>> = _districtsByProvinceId

    private val _wardsByDistrictId = MutableLiveData<List<WardEntity>>()
    val wardsByDistrictId: LiveData<List<WardEntity>> = _wardsByDistrictId

    private val _result: MutableLiveData<String> = MutableLiveData<String>()
    val result: LiveData<String> = _result

    fun getAllProvince() {
        viewModelScope.launch {
            AppLogger.d(TAG, "----------Get all provinces----------")
            _allProvince.value = provinceUseCase.getAllProvince()
        }
    }

    fun getDistrictsByProvinceId(provinceId: Int) {
        viewModelScope.launch {
            AppLogger.d(TAG, "----------Get districts----------")
            _districtsByProvinceId.value = provinceUseCase.getDistrictsByProvinceId(provinceId)
        }
    }

    fun getWardsByDistrictId(districtId: Int) {
        viewModelScope.launch {
            AppLogger.d(TAG, "----------Get wards----------")
            _wardsByDistrictId.value = provinceUseCase.getWardsByDistrictId(districtId)
        }
    }

    fun updateResult(data: String) {
        _result.value = data
    }
}