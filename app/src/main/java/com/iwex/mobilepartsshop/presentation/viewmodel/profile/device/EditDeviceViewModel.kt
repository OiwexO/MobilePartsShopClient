package com.iwex.mobilepartsshop.presentation.viewmodel.profile.device

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshop.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshop.domain.entity.user.device.Device
import com.iwex.mobilepartsshop.domain.entity.user.device.DeviceRequest
import com.iwex.mobilepartsshop.domain.use_case.authentication.GetUserUseCase
import com.iwex.mobilepartsshop.domain.use_case.part.device_type.GetAllDeviceTypesUseCase
import com.iwex.mobilepartsshop.domain.use_case.part.manufacturer.GetAllManufacturersUseCase
import com.iwex.mobilepartsshop.domain.use_case.user.device.CreateDeviceUseCase
import com.iwex.mobilepartsshop.domain.use_case.user.device.GetDeviceByUserIdUseCase
import com.iwex.mobilepartsshop.domain.use_case.user.device.UpdateDeviceUseCase
import com.iwex.mobilepartsshop.presentation.fragment.profile.device.EditDeviceFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditDeviceViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getAllManufacturersUseCase: GetAllManufacturersUseCase,
    private val getAllDeviceTypesUseCase: GetAllDeviceTypesUseCase,
    private val getDeviceByUserIdUseCase: GetDeviceByUserIdUseCase,
    private val createDeviceUseCase: CreateDeviceUseCase,
    private val updateDeviceUseCase: UpdateDeviceUseCase,
) : ViewModel() {

    private var customerId = 0L

    private val _manufacturers = MutableLiveData<List<Manufacturer>>()
    val manufacturers: LiveData<List<Manufacturer>> = _manufacturers

    private val _deviceTypes = MutableLiveData<List<DeviceType>>()
    val deviceTypes: LiveData<List<DeviceType>> = _deviceTypes

    private val _device = MutableLiveData<Device>()
    val device: LiveData<Device> = _device

    private val _formState = MutableLiveData<EditDeviceFormState>()
    val formState: LiveData<EditDeviceFormState> = _formState

    private val _onSuccess = MutableLiveData<Unit>()
    val onSuccess: LiveData<Unit> = _onSuccess

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun loadData() {
        _isLoading.value = true
        getUser()
        getAllManufacturers()
        getAllDeviceTypes()
        getDevice()
        _isLoading.value = false
    }

    fun saveDevice(request: DeviceRequest) {
        _isLoading.value = true
        if (validateDeviceRequest(request)) {
            viewModelScope.launch {
                val result = if (_device.value == null)
                    createDeviceUseCase(customerId, request)
                else
                    updateDeviceUseCase(customerId, request)
                result.onSuccess {
                    _onSuccess.value = Unit
                }.onFailure {
                    Log.e(TAG, it.toString())
                    _errorMessage.value = it.message ?: "Edit device failed"
                }
            }
        }
        _isLoading.value = false
    }

    private fun getUser() {
        viewModelScope.launch {
            val result = getUserUseCase()
            result.onSuccess {
                customerId = it.id
            }.onFailure {
                _errorMessage.value = it.message ?: "Get user failed"
            }
        }
    }

    private fun getAllManufacturers() {
//        _isLoading.value = true
        viewModelScope.launch {
            val result = getAllManufacturersUseCase()
            result.onSuccess {
                _manufacturers.value = it
            }.onFailure {
                Log.e(TAG, it.toString())
                _errorMessage.value = it.message ?: "Get manufacturers failed"
            }
        }
//        _isLoading.value = false
    }

    private fun getAllDeviceTypes() {
//        _isLoading.value = true
        viewModelScope.launch {
            val result = getAllDeviceTypesUseCase()
            result.onSuccess {
                _deviceTypes.value = it
            }.onFailure {
                Log.e(TAG, it.toString())
                _errorMessage.value = it.message ?: "Get device types failed"
            }
        }
//        _isLoading.value = false
    }

    private fun getDevice() {
//        _isLoading.value = true
        viewModelScope.launch {
            val result = getDeviceByUserIdUseCase(customerId)
            result.onSuccess {
                _device.value = it
            }.onFailure {
                Log.e(TAG, it.toString())
                _errorMessage.value = it.message ?: "Get device failed"
            }
        }
//        _isLoading.value = false
    }

    private fun validateDeviceRequest(request: DeviceRequest): Boolean {
        val manufacturerError =
            if (request.manufacturerId <= 0) R.string.invalid_manufacturer else null
        val deviceTypeError =
            if (request.deviceTypeId <= 0) R.string.invalid_device_type else null
        val modelError = if (request.model.isBlank()) R.string.invalid_model else null
        val specificationsError =
            if (request.specifications.isBlank()) R.string.invalid_specifications else null
        val isDataValid = manufacturerError == null &&
                deviceTypeError == null &&
                modelError == null &&
                specificationsError == null
        val formState = EditDeviceFormState(
            isDataValid = isDataValid,
            manufacturerError = manufacturerError,
            deviceTypeError = deviceTypeError,
            modelError = modelError,
            specificationsError = specificationsError
        )
        _formState.value = formState
        return isDataValid
    }

    companion object {

        private const val TAG = "EditDeviceVM"
    }
}