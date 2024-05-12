package com.iwex.mobilepartsshop.domain.use_case.user.address

import com.iwex.mobilepartsshop.domain.entity.user.Address
import com.iwex.mobilepartsshop.domain.entity.user.AddressRequest
import com.iwex.mobilepartsshop.domain.repository.user.AddressRepository
import javax.inject.Inject

class UpdateAddressUseCase @Inject constructor(private val addressRepository: AddressRepository) {

    suspend operator fun invoke(userId: Long, request: AddressRequest): Result<Address> {
        return addressRepository.updateAddress(userId, request)
    }
}