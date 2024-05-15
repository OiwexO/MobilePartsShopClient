package com.iwex.mobilepartsshop.data.remote.dto.mapper.recommendation

import com.iwex.mobilepartsshop.data.remote.dto.mapper.ResponseRequestMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.PartMapper
import com.iwex.mobilepartsshop.data.remote.dto.part.PartResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.recommendation.RecommendationByCriteriaRequestDto
import com.iwex.mobilepartsshop.domain.entity.part.Part
import com.iwex.mobilepartsshop.domain.entity.recommendation.RecommendationByCriteriaRequest
import javax.inject.Inject

class RecommendationByCriteriaMapper @Inject constructor(
    private val partMapper: PartMapper
) : ResponseRequestMapper<Part, RecommendationByCriteriaRequest, PartResponseDto, RecommendationByCriteriaRequestDto>() {

    override fun toEntity(dto: PartResponseDto): Part {
        return partMapper.toEntity(dto)
    }

    override fun toRequestDto(request: RecommendationByCriteriaRequest): RecommendationByCriteriaRequestDto {
        return RecommendationByCriteriaRequestDto(
            manufacturerId = request.manufacturerId,
            deviceTypeId = request.deviceTypeId,
            partTypeId = request.partTypeId,
            sortAscending = request.sortAscending
        )
    }
}