package com.iwex.mobilepartsshop.domain.use_case.user.address

import com.iwex.mobilepartsshop.domain.repository.user.AddressRepository
import javax.inject.Inject

class DeleteAddressByUserIdUseCase @Inject constructor(
    private val addressRepository: AddressRepository
) {

    suspend operator fun invoke(userId: Long): Result<Unit> {
        return addressRepository.deleteAddressByUserId(userId)
    }
}
