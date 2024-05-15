package com.iwex.mobilepartsshop.data.remote.dto.mapper.user.address

import com.iwex.mobilepartsshop.data.remote.dto.mapper.ResponseRequestMapper
import com.iwex.mobilepartsshop.data.remote.dto.user.address.AddressRequestDto
import com.iwex.mobilepartsshop.data.remote.dto.user.address.AddressResponseDto
import com.iwex.mobilepartsshop.domain.entity.user.address.Address
import com.iwex.mobilepartsshop.domain.entity.user.address.AddressRequest

class AddressMapper : ResponseRequestMapper<Address, AddressRequest, AddressResponseDto, AddressRequestDto>() {

    override fun toEntity(dto: AddressResponseDto): Address {
        return Address(
            id = dto.id,
            postalCode = dto.postalCode,
            country = dto.country,
            state = dto.state,
            city = dto.city,
            street = dto.street,
            buildingNumber = dto.buildingNumber
        )
    }

    override fun toRequestDto(request: AddressRequest): AddressRequestDto {
        return AddressRequestDto(
            postalCode = request.postalCode,
            country = request.country,
            state = request.state,
            city = request.city,
            street = request.street,
            buildingNumber = request.buildingNumber
        )
    }
}