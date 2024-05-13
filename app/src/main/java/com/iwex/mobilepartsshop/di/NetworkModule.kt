package com.iwex.mobilepartsshop.di

import com.iwex.mobilepartsshop.data.remote.ApiConstants
import com.iwex.mobilepartsshop.data.remote.AuthenticationApiService
import com.iwex.mobilepartsshop.data.remote.MainApiService
import com.iwex.mobilepartsshop.data.remote.dto.mapper.authentication.AuthenticationMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.authentication.RegistrationMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.order.OrderItemMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.order.OrderMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.PartMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.device_type.DeviceTypeMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.manufacturer.ManufacturerMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.part_type.PartTypeMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.review.ReviewMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.user.address.AddressMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.user.UserMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.user.device.DeviceMapper
import com.iwex.mobilepartsshop.data.remote.interceptor.AccessTokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @[Provides Singleton AuthenticatedClient]
    fun provideAuthenticatedOkHttpClient(
        accessTokenInterceptor: AccessTokenInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(accessTokenInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @[Provides Singleton UnauthenticatedClient]
    fun provideUnauthenticatedOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @[Provides Singleton]
    fun provideMainApiService(@AuthenticatedClient okHttpClient: OkHttpClient): MainApiService {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MainApiService::class.java)

    }

    @[Provides Singleton]
    fun provideAuthenticationApiService(@UnauthenticatedClient okHttpClient: OkHttpClient): AuthenticationApiService {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AuthenticationApiService::class.java)
    }

    @[Provides Singleton]
    fun provideAuthenticationMapper(userMapper: UserMapper): AuthenticationMapper =
        AuthenticationMapper(userMapper)

    @[Provides Singleton]
    fun provideRegistrationMapper(userMapper: UserMapper): RegistrationMapper =
        RegistrationMapper(userMapper)

    @[Provides Singleton]
    fun provideDeviceTypeMapper(): DeviceTypeMapper = DeviceTypeMapper()

    @[Provides Singleton]
    fun provideManufacturerMapper(): ManufacturerMapper = ManufacturerMapper()

    @[Provides Singleton]
    fun providePartTypeMapper(): PartTypeMapper = PartTypeMapper()

    @[Provides Singleton]
    fun providePartMapper(
        manufacturerMapper: ManufacturerMapper,
        deviceTypeMapper: DeviceTypeMapper,
        partTypeMapper: PartTypeMapper,
    ): PartMapper = PartMapper(manufacturerMapper, deviceTypeMapper, partTypeMapper)

    @[Provides Singleton]
    fun provideReviewMapper(
        userMapper: UserMapper
    ): ReviewMapper = ReviewMapper(userMapper)

    @[Provides Singleton]
    fun provideUserMapper(): UserMapper = UserMapper()

    @[Provides Singleton]
    fun provideAddressMapper(): AddressMapper = AddressMapper()

    @[Provides Singleton]
    fun provideDeviceMapper(
        manufacturerMapper: ManufacturerMapper,
        deviceTypeMapper: DeviceTypeMapper
    ): DeviceMapper = DeviceMapper(manufacturerMapper, deviceTypeMapper)

    @[Provides Singleton]
    fun provideOrderItemMapper(partMapper: PartMapper): OrderItemMapper =
        OrderItemMapper(partMapper)

    @[Provides Singleton]
    fun provideOrderMapper(
        orderItemMapper: OrderItemMapper,
        addressMapper: AddressMapper
    ): OrderMapper = OrderMapper(orderItemMapper, addressMapper)
}