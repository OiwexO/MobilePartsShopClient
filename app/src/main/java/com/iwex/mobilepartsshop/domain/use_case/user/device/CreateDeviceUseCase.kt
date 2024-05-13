package com.iwex.mobilepartsshop.domain.use_case.user.device

import com.iwex.mobilepartsshop.domain.entity.user.device.Device
import com.iwex.mobilepartsshop.domain.entity.user.device.DeviceRequest
import com.iwex.mobilepartsshop.domain.repository.user.DeviceRepository
import javax.inject.Inject

class CreateDeviceUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {

    suspend operator fun invoke(userId: Long, request: DeviceRequest): Result<Device> {
        return deviceRepository.createDevice(userId, request)
    }
}