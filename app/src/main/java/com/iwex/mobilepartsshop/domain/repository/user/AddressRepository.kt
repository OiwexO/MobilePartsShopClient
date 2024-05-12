package com.iwex.mobilepartsshop.domain.repository.user

import com.iwex.mobilepartsshop.domain.entity.user.Address
import com.iwex.mobilepartsshop.domain.entity.user.AddressRequest

interface AddressRepository {

    fun getAddressByUserId(userId: Long): Result<Address>

    fun createAddress(userId: Long, request: AddressRequest): Result<Address>

    fun updateAddress(userId: Long, request: AddressRequest): Result<Address>

    fun deleteAddressByUserId(userId: Long): Result<Unit>
}