package com.iwex.mobilepartsshop.data.remote.dto.mapper.recommendation

import com.iwex.mobilepartsshop.data.remote.dto.mapper.ResponseRequestMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.PartMapper
import com.iwex.mobilepartsshop.data.remote.dto.part.PartResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.recommendation.RecommendationByDeviceRequestDto
import com.iwex.mobilepartsshop.domain.entity.part.Part
import com.iwex.mobilepartsshop.domain.entity.recommendation.RecommendationByDeviceRequest
import javax.inject.Inject

class RecommendationByDeviceMapper @Inject constructor(
    private val partMapper: PartMapper
) : ResponseRequestMapper<Part, RecommendationByDeviceRequest, PartResponseDto, RecommendationByDeviceRequestDto>() {

    override fun toEntity(dto: PartResponseDto): Part {
        return partMapper.toEntity(dto)
    }

    override fun toRequestDto(request: RecommendationByDeviceRequest): RecommendationByDeviceRequestDto {
        return RecommendationByDeviceRequestDto(
            deviceId = request.deviceId,
            sortAscending = request.sortAscending
        )
    }
}