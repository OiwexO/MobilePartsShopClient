package com.iwex.mobilepartsshop.presentation.viewmodel.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshop.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshop.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshop.domain.entity.part.part_type.PartType
import com.iwex.mobilepartsshop.domain.use_case.authentication.GetUserUseCase
import com.iwex.mobilepartsshop.domain.use_case.part.device_type.GetAllDeviceTypesUseCase
import com.iwex.mobilepartsshop.domain.use_case.part.manufacturer.GetAllManufacturersUseCase
import com.iwex.mobilepartsshop.domain.use_case.part.part_type.GetAllPartTypesUseCase
import com.iwex.mobilepartsshop.domain.use_case.user.device.GetDeviceByUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPartsViewModel @Inject constructor(
    private val getAllManufacturersUseCase: GetAllManufacturersUseCase,
    private val getAllDeviceTypesUseCase: GetAllDeviceTypesUseCase,
    private val getAllPartTypesUseCase: GetAllPartTypesUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getDeviceByUserIdUseCase: GetDeviceByUserIdUseCase

) : ViewModel() {

    private val _deviceTypes = MutableLiveData<List<DeviceType>>()
    val deviceTypes: LiveData<List<DeviceType>> = _deviceTypes

    private val _manufacturers = MutableLiveData<List<Manufacturer>>()
    val manufacturers: LiveData<List<Manufacturer>> = _manufacturers

    private val _partTypes = MutableLiveData<List<PartType>>()
    val partTypes: LiveData<List<PartType>> = _partTypes

    private val _deviceId = MutableLiveData<Long>()
    val deviceId: LiveData<Long> = _deviceId

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun loadData() {
        getAllManufacturers()
        getAllDeviceTypes()
        getAllPartTypes()
        getDeviceId()
    }

    private fun getAllManufacturers() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getAllManufacturersUseCase()
            result.onSuccess {
                _manufacturers.value = it
            }.onFailure {
                Log.e(TAG, it.toString())
                _errorMessage.value = it.message ?: "Get manufacturers failed"
            }
        }
        _isLoading.value = false
    }

    private fun getAllDeviceTypes() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getAllDeviceTypesUseCase()
            result.onSuccess {
                _deviceTypes.value = it
            }.onFailure {
                Log.e(TAG, it.toString())
                _errorMessage.value = it.message ?: "Get device types failed"
            }
        }
        _isLoading.value = false
    }

    private fun getAllPartTypes() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getAllPartTypesUseCase()
            result.onSuccess {
                _partTypes.value = it
            }.onFailure {
                Log.e(TAG, it.toString())
                _errorMessage.value = it.message ?: "Get part types failed"
            }
        }
        _isLoading.value = false
    }

    private fun getDeviceId() {
        _isLoading.value = true
        viewModelScope.launch {
            val userResult = getUserUseCase()
            userResult.onSuccess { user ->
                val deviceResult = getDeviceByUserIdUseCase(user.id)
                deviceResult.onSuccess {
                    _deviceId.value = it.id
                }.onFailure {
                    Log.e(TAG, it.toString())
                    _errorMessage.value = it.message ?: "Get device failed"
                }
            }.onFailure {
                _errorMessage.value = it.message ?: "Get user failed"
            }


        }
        _isLoading.value = false
    }

    companion object {

        private const val TAG = "SearchPartsVm"
    }
}