package com.iwex.mobilepartsshop.domain.use_case.user.device

import com.iwex.mobilepartsshop.domain.repository.user.DeviceRepository
import javax.inject.Inject

class DeleteDeviceByUserIdUseCase @Inject constructor(
    private val deviceRepository: DeviceRepository
) {

    suspend operator fun invoke(userId: Long): Result<Unit> {
        return deviceRepository.deleteDeviceByUserId(userId)
    }
}
