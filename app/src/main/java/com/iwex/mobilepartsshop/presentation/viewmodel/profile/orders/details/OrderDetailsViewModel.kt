package com.iwex.mobilepartsshop.presentation.viewmodel.profile.orders.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshop.domain.entity.order.Order
import com.iwex.mobilepartsshop.domain.entity.order.OrderStatus
import com.iwex.mobilepartsshop.domain.use_case.order.GetOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
    private val getOrderUseCase: GetOrderUseCase,
) : ViewModel() {

    private val _order = MutableLiveData<Order>()
    val order: LiveData<Order> = _order

    private val _canCancelOrder = MutableLiveData<Boolean>()
    val canCancelOrder: LiveData<Boolean> = _canCancelOrder

    private val _onSuccess = MutableLiveData<Unit>()
    val onSuccess: LiveData<Unit> = _onSuccess

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getOrder(orderId: Long) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getOrderUseCase(orderId)
            result.onSuccess {
                _order.value = it
                _canCancelOrder.value = canCancelOrder(it)
            }.onFailure {
                Log.d(TAG, it.toString())
                _errorMessage.value = it.message ?: "Get order failed"
            }
        }
        _isLoading.value = false
    }

    //TODO implement order cancellation
    fun cancelOrder() {

    }

    private fun canCancelOrder(order: Order) =
        order.status != OrderStatus.COMPLETED && order.status != OrderStatus.CANCELED

    companion object {

        private const val TAG = "OrderDetailsVm"
    }
}