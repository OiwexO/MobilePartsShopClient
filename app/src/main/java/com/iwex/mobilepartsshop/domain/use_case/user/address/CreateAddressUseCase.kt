package com.iwex.mobilepartsshop.domain.use_case.user.address

import com.iwex.mobilepartsshop.domain.entity.user.address.Address
import com.iwex.mobilepartsshop.domain.entity.user.address.AddressRequest
import com.iwex.mobilepartsshop.domain.repository.user.AddressRepository
import javax.inject.Inject

class CreateAddressUseCase @Inject constructor(private val addressRepository: AddressRepository) {

    suspend operator fun invoke(userId: Long, request: AddressRequest): Result<Address> {
        return addressRepository.createAddress(userId, request)
    }
}