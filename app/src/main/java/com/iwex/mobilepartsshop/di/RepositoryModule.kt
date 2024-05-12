package com.iwex.mobilepartsshop.di

import android.content.Context
import android.content.SharedPreferences
import com.iwex.mobilepartsshop.data.remote.AuthenticationApiService
import com.iwex.mobilepartsshop.data.remote.MainApiService
import com.iwex.mobilepartsshop.data.remote.dto.mapper.authentication.AuthenticationMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.order.OrderMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.PartMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.device_type.DeviceTypeMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.manufacturer.ManufacturerMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.part_type.PartTypeMapper
import com.iwex.mobilepartsshop.data.repository.authentication.AuthenticationRepositoryImpl
import com.iwex.mobilepartsshop.data.repository.order.OrderRepositoryImpl
import com.iwex.mobilepartsshop.data.repository.part.DeviceTypeRepositoryImpl
import com.iwex.mobilepartsshop.data.repository.part.ManufacturerRepositoryImpl
import com.iwex.mobilepartsshop.data.repository.part.PartRepositoryImpl
import com.iwex.mobilepartsshop.data.repository.part.PartTypeRepositoryImpl
import com.iwex.mobilepartsshop.domain.repository.authentication.AuthenticationRepository
import com.iwex.mobilepartsshop.domain.repository.order.OrderRepository
import com.iwex.mobilepartsshop.domain.repository.part.DeviceTypeRepository
import com.iwex.mobilepartsshop.domain.repository.part.ManufacturerRepository
import com.iwex.mobilepartsshop.domain.repository.part.PartRepository
import com.iwex.mobilepartsshop.domain.repository.part.PartTypeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    private const val SHARED_PREFS_NAME = "jwt_prefs"

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences =
        appContext.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    @[Provides Singleton]
    fun provideAuthenticationRepository(
        apiService: AuthenticationApiService,
        preferences: SharedPreferences,
        mapper: AuthenticationMapper
    ): AuthenticationRepository = AuthenticationRepositoryImpl(apiService, preferences, mapper)

    @[Provides Singleton]
    fun provideDeviceTypeRepository(
        apiService: MainApiService,
        mapper: DeviceTypeMapper
    ): DeviceTypeRepository = DeviceTypeRepositoryImpl(apiService, mapper)

    @[Provides Singleton]
    fun provideManufacturerRepository(
        apiService: MainApiService,
        mapper: ManufacturerMapper
    ): ManufacturerRepository = ManufacturerRepositoryImpl(apiService, mapper)

    @[Provides Singleton]
    fun providePartRepository(
        apiService: MainApiService,
        mapper: PartMapper
    ): PartRepository = PartRepositoryImpl(apiService, mapper)

    @[Provides Singleton]
    fun providePartTypeRepository(
        apiService: MainApiService,
        mapper: PartTypeMapper
    ): PartTypeRepository = PartTypeRepositoryImpl(apiService, mapper)

    @[Provides Singleton]
    fun provideOrderRepository(
        apiService: MainApiService,
        mapper: OrderMapper
    ): OrderRepository = OrderRepositoryImpl(apiService, mapper)
}