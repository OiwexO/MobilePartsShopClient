package com.iwex.mobilepartsshop.presentation.viewmodel.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.authentication.AuthenticationRequest
import com.iwex.mobilepartsshop.domain.entity.user.User
import com.iwex.mobilepartsshop.domain.use_case.authentication.AuthenticateUserUseCase
import com.iwex.mobilepartsshop.presentation.fragment.login.LoginFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticateUserUseCase: AuthenticateUserUseCase
) : ViewModel() {

    private val _loginFormState = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginFormState

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun login(authenticationRequest: AuthenticationRequest) {
        _isLoading.value = true
        if (validateAuthenticationRequest(authenticationRequest)) {
            viewModelScope.launch {
                val result = authenticateUserUseCase(authenticationRequest)
                result.onSuccess {
                    _user.value = it.user
                }.onFailure {
                    _errorMessage.value = it.message ?: "Login failed"
                }
            }
        }
        _isLoading.value = false
    }

    private fun validateAuthenticationRequest(request: AuthenticationRequest): Boolean {
        val usernameError = if (!isUsernameValid(request.username)) R.string.invalid_username else null
        val passwordError = if (!isPasswordValid(request.password)) R.string.invalid_password else null
        val isDataValid = usernameError == null && passwordError == null
        val formState = LoginFormState(usernameError, passwordError, isDataValid)
        _loginFormState.value = formState
        return isDataValid
    }

    private fun isUsernameValid(username: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(username).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}