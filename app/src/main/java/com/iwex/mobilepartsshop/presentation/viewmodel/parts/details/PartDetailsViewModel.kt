package com.iwex.mobilepartsshop.presentation.viewmodel.parts.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshop.domain.entity.part.Part
import com.iwex.mobilepartsshop.domain.entity.review.Review
import com.iwex.mobilepartsshop.domain.use_case.cart.AddPartToCartUseCase
import com.iwex.mobilepartsshop.domain.use_case.review.GetReviewsByPartIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartDetailsViewModel @Inject constructor(
    private val getReviewsByPartIdUseCase: GetReviewsByPartIdUseCase,
    private val addPartToCartUseCase: AddPartToCartUseCase
) : ViewModel() {

    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>> = _reviews

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _onAddedToCart = MutableLiveData<Unit>()
    val onAddedToCart: LiveData<Unit> = _onAddedToCart

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun loadReviews(partId: Long) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getReviewsByPartIdUseCase(partId)
            result.onSuccess {
                _reviews.value = it
            }.onFailure {
                Log.d(TAG, it.toString())
                _errorMessage.value = it.message ?: "Get reviews by part id failed"
            }
        }
        _isLoading.value = false
    }

    fun addPartToCart(part: Part) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = addPartToCartUseCase(part)
            result.onSuccess {
                _onAddedToCart.value = Unit
            }.onFailure {
                Log.d(TAG, it.toString())
                _errorMessage.value = it.message ?: "Add part to cart failed"
            }
        }
        _isLoading.value = false
    }

    companion object {
        private const val TAG = "PartDetailsVm"
    }
}