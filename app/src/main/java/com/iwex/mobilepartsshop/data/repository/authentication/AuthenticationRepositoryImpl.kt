package com.iwex.mobilepartsshop.data.repository.authentication

import android.content.SharedPreferences
import com.iwex.mobilepartsshop.data.remote.AuthenticationApiService
import com.iwex.mobilepartsshop.data.remote.dto.mapper.authentication.AuthenticationMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.authentication.RegistrationMapper
import com.iwex.mobilepartsshop.domain.entity.authentication.AuthenticationRequest
import com.iwex.mobilepartsshop.domain.entity.authentication.AuthenticationResponse
import com.iwex.mobilepartsshop.domain.entity.authentication.Jwt
import com.iwex.mobilepartsshop.domain.entity.authentication.RegistrationRequest
import com.iwex.mobilepartsshop.domain.entity.user.User
import com.iwex.mobilepartsshop.domain.entity.user.UserAuthority
import com.iwex.mobilepartsshop.domain.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val apiService: AuthenticationApiService,
    private val preferences: SharedPreferences,
    private val authenticationMapper: AuthenticationMapper,
    private val registrationMapper: RegistrationMapper
) : AuthenticationRepository {

    override suspend fun registerUser(request: RegistrationRequest): Result<AuthenticationResponse> {
        val requestDto = registrationMapper.toRequestDto(request)
        val response = try {
            apiService.registerUser(requestDto)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = authenticationMapper.toEntity(response)
        saveUser(entity.user)
        saveJwt(entity.jwtToken)
        return Result.success(entity)
    }

    override suspend fun authenticateUser(request: AuthenticationRequest): Result<AuthenticationResponse> {
        val requestDto = authenticationMapper.toRequestDto(request)
        val response = try {
            apiService.authenticateUser(requestDto)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = authenticationMapper.toEntity(response)
        saveUser(entity.user)
        saveJwt(entity.jwtToken)
        return Result.success(entity)
    }

    override suspend fun isAuthenticated(): Result<Boolean> {
        val isAuthenticated = preferences.contains(KEY_JWT)
        return Result.success(isAuthenticated)
    }

    override suspend fun getJwt(): Jwt {
        val jwt = preferences.getString(KEY_JWT, null) ?: ""
        return Jwt(jwt)
    }

    override suspend fun getUser(): Result<User> {
        val id = preferences.getLong(KEY_ID, 0)
        val username = preferences.getString(KEY_USERNAME, null)
        val firstname = preferences.getString(KEY_FIRSTNAME, null)
        val lastname = preferences.getString(KEY_LASTNAME, null)
        if (id == 0L || username == null || firstname == null || lastname == null) {
            return Result.failure(Exception())
        }
        return Result.success(
            User(
                id = id,
                username = username,
                firstname = firstname,
                lastname = lastname,
                authority = UserAuthority.CUSTOMER
            )
        )
    }

    override suspend fun logout(): Result<Unit> {
        preferences.edit().clear().apply()
        return Result.success(Unit)
    }

    private fun saveUser(user: User) {
        preferences.edit()
            .putLong(KEY_ID, user.id)
            .putString(KEY_USERNAME, user.username)
            .putString(KEY_FIRSTNAME, user.firstname)
            .putString(KEY_LASTNAME, user.lastname)
            .apply()
    }

    private fun saveJwt(jwt: Jwt) {
        preferences.edit().putString(KEY_JWT, jwt.value).apply()
    }

    companion object {

        private const val KEY_ID = "id"

        private const val KEY_USERNAME = "username"

        private const val KEY_FIRSTNAME = "firstname"

        private const val KEY_LASTNAME = "lastname"

        private const val KEY_JWT = "jwt"
    }
}