package com.iwex.mobilepartsshop.data.remote

import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.ADDRESSES_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.DEVICES_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.DEVICE_TYPES_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.MANUFACTURERS_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.ORDERS_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.PARTS_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.PART_TYPES_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.REVIEWS_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.dto.order.OrderRequestDto
import com.iwex.mobilepartsshop.data.remote.dto.order.OrderResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.part.PartResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.part.device_type.DeviceTypeResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.part.manufacturer.ManufacturerResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.part.part_type.PartTypeResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.review.ReviewRequestDto
import com.iwex.mobilepartsshop.data.remote.dto.review.ReviewResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.user.address.AddressRequestDto
import com.iwex.mobilepartsshop.data.remote.dto.user.address.AddressResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.user.device.DeviceRequestDto
import com.iwex.mobilepartsshop.data.remote.dto.user.device.DeviceResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MainApiService {

    // orders

    @GET("$ORDERS_MAPPING_V1/customer/{customerId}")
    suspend fun getOrdersByCustomerId(@Path("customerId") customerId: Long): List<OrderResponseDto>

    @GET("$ORDERS_MAPPING_V1/{orderId}")
    suspend fun getOrder(@Path("orderId") orderId: Long): OrderResponseDto

    @POST("$ORDERS_MAPPING_V1/customer/{customerId}")
    suspend fun createOrder(
        @Path("customerId") customerId: Long,
        @Body request: OrderRequestDto
    ): OrderResponseDto

    @PUT("$ORDERS_MAPPING_V1/{orderId}")
    suspend fun updateOrder(
        @Path("orderId") orderId: Long,
        @Body request: OrderRequestDto
    ): OrderResponseDto

    @DELETE("$ORDERS_MAPPING_V1/{orderId}")
    suspend fun deleteOrder(@Path("orderId") orderId: Long)

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

    // reviews

    @GET("$REVIEWS_MAPPING_V1/author/{authorId}")
    suspend fun getAllReviewsForUser(@Path("authorId") authorId: Long): List<ReviewResponseDto>

    @GET("$REVIEWS_MAPPING_V1/part/{partId}")
    suspend fun getAllReviewsForPart(@Path("partId") partId: Long): List<ReviewResponseDto>

    @GET("$REVIEWS_MAPPING_V1/{reviewId}")
    suspend fun getReview(@Path("reviewId") reviewId: Long): ReviewResponseDto

    @POST(REVIEWS_MAPPING_V1)
    suspend fun createReview(@Body request: ReviewRequestDto): ReviewResponseDto

    @PUT("$REVIEWS_MAPPING_V1/{reviewId}")
    suspend fun updateReview(
        @Path("reviewId") reviewId: Long,
        @Body request: ReviewRequestDto
    ): ReviewResponseDto

    @DELETE("$REVIEWS_MAPPING_V1/{reviewId}")
    suspend fun deleteReview(@Path("reviewId") reviewId: Long)

    // addresses

    @GET("$ADDRESSES_MAPPING_V1/user/{userId}")
    suspend fun getAddressByUserId(@Path("userId") userId: Long): AddressResponseDto

    @POST("$ADDRESSES_MAPPING_V1/user/{userId}")
    suspend fun createAddress(
        @Path("userId") userId: Long,
        @Body request: AddressRequestDto
    ): AddressResponseDto

    @PUT("$ADDRESSES_MAPPING_V1/user/{userId}")
    suspend fun updateAddress(
        @Path("userId") userId: Long,
        @Body request: AddressRequestDto
    ): AddressResponseDto

    @DELETE("$ADDRESSES_MAPPING_V1/user/{userId}")
    suspend fun deleteAddressByUserId(@Path("userId") userId: Long)

    // devices

    @GET("$DEVICES_MAPPING_V1/user/{userId}")
    suspend fun getDeviceByUserId(@Path("userId") userId: Long): DeviceResponseDto

    @POST("$DEVICES_MAPPING_V1/user/{userId}")
    suspend fun createDevice(
        @Path("userId") userId: Long,
        @Body request: DeviceRequestDto
    ): DeviceResponseDto

    @PUT("$DEVICES_MAPPING_V1/user/{userId}")
    suspend fun updateDevice(
        @Path("userId") userId: Long,
        @Body request: DeviceRequestDto
    ): DeviceResponseDto

    @DELETE("$DEVICES_MAPPING_V1/user/{userId}")
    suspend fun deleteDeviceByUserId(@Path("userId") userId: Long)
}