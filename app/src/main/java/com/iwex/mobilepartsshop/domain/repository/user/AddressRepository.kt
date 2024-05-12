package com.iwex.mobilepartsshop.domain.repository.user

import com.iwex.mobilepartsshop.domain.entity.user.Address
import com.iwex.mobilepartsshop.domain.entity.user.AddressRequest

interface AddressRepository {

    suspend fun getAddressByUserId(userId: Long): Result<Address>

    suspend fun createAddress(userId: Long, request: AddressRequest): Result<Address>

    suspend fun updateAddress(userId: Long, request: AddressRequest): Result<Address>

    suspend fun deleteAddressByUserId(userId: Long): Result<Unit>
}