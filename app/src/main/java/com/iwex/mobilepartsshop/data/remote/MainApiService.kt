package com.iwex.mobilepartsshop.data.remote

import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.DEVICE_TYPES_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.MANUFACTURERS_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.PARTS_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.PART_TYPES_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.dto.order.OrderResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.part.PartResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.part.device_type.DeviceTypeResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.part.manufacturer.ManufacturerResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.part.part_type.PartTypeResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface MainApiService {

    // device types

    @GET(DEVICE_TYPES_MAPPING_V1)
    suspend fun getAllDeviceTypes(): List<DeviceTypeResponseDto>

    @GET("$DEVICE_TYPES_MAPPING_V1/{deviceTypeId}")
    suspend fun getDeviceType(@Path("deviceTypeId") deviceTypeId: Long): DeviceTypeResponseDto

    // manufacturers

    @GET(MANUFACTURERS_MAPPING_V1)
    suspend fun getAllManufacturers(): List<ManufacturerResponseDto>

    @GET("$MANUFACTURERS_MAPPING_V1/{manufacturerId}")
    suspend fun getManufacturer(@Path("manufacturerId") manufacturerId: Long): ManufacturerResponseDto

    // part types

    @GET(PART_TYPES_MAPPING_V1)
    suspend fun getAllPartTypes(): List<PartTypeResponseDto>

    @GET("$PART_TYPES_MAPPING_V1/{partTypeId}")
    suspend fun getPartType(@Path("partTypeId") partTypeId: Long): PartTypeResponseDto

    // parts

    @GET(PARTS_MAPPING_V1)
    suspend fun getAllParts(): List<PartResponseDto>

    @GET("$PARTS_MAPPING_V1/{partId}")
    suspend fun getPart(@Path("partId") partId: Long): PartResponseDto

}