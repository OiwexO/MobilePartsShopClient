package com.iwex.mobilepartsshop.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshop.domain.use_case.authentication.CheckIsUserAuthenticatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkIsUserAuthenticatedUseCase: CheckIsUserAuthenticatedUseCase
) : ViewModel() {

    private val _isUserAuthenticated = MutableLiveData<Boolean>()
    val isUserAuthenticated: LiveData<Boolean> = _isUserAuthenticated

    fun checkUserAuthentication() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                checkIsUserAuthenticatedUseCase()
            }
            result.onSuccess {
                _isUserAuthenticated.value = it
            }.onFailure {
                _isUserAuthenticated.value = false
            }
        }
    }
}