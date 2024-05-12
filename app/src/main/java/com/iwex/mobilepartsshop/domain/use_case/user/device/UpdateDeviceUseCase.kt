package com.iwex.mobilepartsshop.domain.use_case.user.device

import com.iwex.mobilepartsshop.domain.entity.user.Device
import com.iwex.mobilepartsshop.domain.entity.user.DeviceRequest
import com.iwex.mobilepartsshop.domain.repository.user.DeviceRepository
import javax.inject.Inject

class UpdateDeviceUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {

    suspend operator fun invoke(userId: Long, request: DeviceRequest): Result<Device> {
        return deviceRepository.updateDevice(userId, request)
    }
}