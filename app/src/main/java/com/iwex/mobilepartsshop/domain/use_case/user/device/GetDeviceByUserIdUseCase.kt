package com.iwex.mobilepartsshop.domain.use_case.user.device

import com.iwex.mobilepartsshop.domain.entity.user.Device
import com.iwex.mobilepartsshop.domain.repository.user.DeviceRepository
import javax.inject.Inject

class GetDeviceByUserIdUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {

    suspend operator fun invoke(userId: Long): Result<Device> {
        return deviceRepository.getDeviceByUserId(userId)
    }
}