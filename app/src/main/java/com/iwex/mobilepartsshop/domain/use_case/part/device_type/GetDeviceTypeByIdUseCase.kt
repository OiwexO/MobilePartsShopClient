package com.iwex.mobilepartsshop.domain.use_case.part.device_type

import com.iwex.mobilepartsshop.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshop.domain.repository.part.device_type.DeviceTypeRepository
import javax.inject.Inject

class GetDeviceTypeByIdUseCase @Inject constructor(
    private val deviceTypeRepository: DeviceTypeRepository
) {
    suspend operator fun invoke(id: Long): Result<DeviceType> {
        return deviceTypeRepository.getDeviceType(id)
    }
}