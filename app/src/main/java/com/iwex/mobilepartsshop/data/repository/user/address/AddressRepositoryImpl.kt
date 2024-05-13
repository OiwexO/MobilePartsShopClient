package com.iwex.mobilepartsshop.data.repository.user.address

import com.iwex.mobilepartsshop.data.remote.MainApiService
import com.iwex.mobilepartsshop.data.remote.dto.mapper.user.address.AddressMapper
import com.iwex.mobilepartsshop.domain.entity.user.address.Address
import com.iwex.mobilepartsshop.domain.entity.user.address.AddressRequest
import com.iwex.mobilepartsshop.domain.repository.user.AddressRepository
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val mapper: AddressMapper
) : AddressRepository {

    override suspend fun getAddressByUserId(userId: Long): Result<Address> {
        val response = try {
            apiService.getAddressByUserId(userId)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun createAddress(userId: Long, request: AddressRequest): Result<Address> {
        val requestDto = mapper.toRequestDto(request)
        val response = try {
            apiService.createAddress(userId, requestDto)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun updateAddress(userId: Long, request: AddressRequest): Result<Address> {
        val requestDto = mapper.toRequestDto(request)
        val response = try {
            apiService.updateAddress(userId, requestDto)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun deleteAddressByUserId(userId: Long): Result<Unit> {
        return try {
            apiService.deleteAddressByUserId(userId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}