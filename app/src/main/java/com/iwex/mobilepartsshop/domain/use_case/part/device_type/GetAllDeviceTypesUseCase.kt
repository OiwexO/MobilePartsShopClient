package com.iwex.mobilepartsshop.domain.use_case.part.device_type

import com.iwex.mobilepartsshop.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshop.domain.repository.part.device_type.DeviceTypeRepository
import javax.inject.Inject

class GetAllDeviceTypesUseCase @Inject constructor(
    private val deviceTypeRepository: DeviceTypeRepository
) {
    suspend operator fun invoke(): Result<List<DeviceType>> {
        return deviceTypeRepository.getAllDeviceTypes()
    }
}