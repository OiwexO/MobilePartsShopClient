package com.iwex.mobilepartsshop.presentation.viewmodel.parts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshop.domain.entity.part.Part
import com.iwex.mobilepartsshop.domain.entity.recommendation.RecommendationByCriteriaRequest
import com.iwex.mobilepartsshop.domain.entity.recommendation.RecommendationByDeviceRequest
import com.iwex.mobilepartsshop.domain.use_case.part.GetAllPartsUseCase
import com.iwex.mobilepartsshop.domain.use_case.recommendation.GetPartsByCriteriaUseCase
import com.iwex.mobilepartsshop.domain.use_case.recommendation.GetPartsByDeviceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartsViewModel @Inject constructor(
    private val getAllPartsUseCase: GetAllPartsUseCase,
    private val getPartsByCriteriaUseCase: GetPartsByCriteriaUseCase,
    private val getPartsByDeviceUseCase: GetPartsByDeviceUseCase
) : ViewModel() {

    private val _parts = MutableLiveData<List<Part>>()
    val parts: LiveData<List<Part>> = _parts

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun loadParts(
        recommendationByCriteriaRequest: RecommendationByCriteriaRequest? = null,
        recommendationByDeviceRequest: RecommendationByDeviceRequest? = null
    ) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = if (recommendationByCriteriaRequest != null) {
                getPartsByCriteriaUseCase(recommendationByCriteriaRequest)
            } else if (recommendationByDeviceRequest != null) {
                getPartsByDeviceUseCase(recommendationByDeviceRequest)
            } else {
                getAllPartsUseCase()
            }
            result.onSuccess {
                _parts.value = it
            }.onFailure {
                Log.d(TAG, it.toString())
                _errorMessage.value = it.message ?: "Get parts failed"
            }
        }
        _isLoading.value = false
    }

    companion object {
        private const val TAG = "PartsVm"
    }
}