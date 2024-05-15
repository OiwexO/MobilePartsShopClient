package com.iwex.mobilepartsshop.presentation.viewmodel.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshop.domain.entity.order.Order
import com.iwex.mobilepartsshop.domain.entity.order.OrderItem
import com.iwex.mobilepartsshop.domain.entity.order.OrderRequest
import com.iwex.mobilepartsshop.domain.entity.user.User
import com.iwex.mobilepartsshop.domain.entity.user.address.Address
import com.iwex.mobilepartsshop.domain.use_case.authentication.GetUserUseCase
import com.iwex.mobilepartsshop.domain.use_case.cart.ChangePartQuantityUseCase
import com.iwex.mobilepartsshop.domain.use_case.cart.DeletePartFromCartUseCase
import com.iwex.mobilepartsshop.domain.use_case.cart.EmptyCartUseCase
import com.iwex.mobilepartsshop.domain.use_case.cart.FormOrderRequestUseCase
import com.iwex.mobilepartsshop.domain.use_case.cart.GetAllOrderItemsUseCase
import com.iwex.mobilepartsshop.domain.use_case.cart.GetOrderPriceUseCase
import com.iwex.mobilepartsshop.domain.use_case.order.CreateOrderUseCase
import com.iwex.mobilepartsshop.domain.use_case.user.address.GetAddressByUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getAllOrderItemsUseCase: GetAllOrderItemsUseCase,
    private val changePartQuantityUseCase: ChangePartQuantityUseCase,
    private val deletePartFromCartUseCase: DeletePartFromCartUseCase,
    private val formOrderRequestUseCase: FormOrderRequestUseCase,
    private val getOrderPriceUseCase: GetOrderPriceUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getAddressByUserIdUseCase: GetAddressByUserIdUseCase,
    private val createOrderUseCase: CreateOrderUseCase,
    private val emptyCartUseCase: EmptyCartUseCase
) : ViewModel() {

    private val _orderItems = MutableLiveData<List<OrderItem>>()
    val orderItems: LiveData<List<OrderItem>> = _orderItems

    private val _orderPrice = MutableLiveData<Double>()
    val orderPrice: LiveData<Double> = _orderPrice

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _onSuccess = MutableLiveData<Unit>()
    val onSuccess: LiveData<Unit> = _onSuccess

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun loadOrderItems() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getAllOrderItemsUseCase()
            result.onSuccess {
                _orderItems.value = it
            }.onFailure {
                Log.d(TAG, it.toString())
                _errorMessage.value = it.message ?: "Get order items failed"
            }
        }
        _isLoading.value = false
    }

    fun changePartQuantity(partId: Long, quantity: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = changePartQuantityUseCase(partId, quantity)
            result.onSuccess {
                calculateOrderPrice()
            }.onFailure {
                Log.d(TAG, it.toString())
                _errorMessage.value = it.message ?: "Change part quantity failed"
            }
        }
        _isLoading.value = false
    }

    fun calculateOrderPrice() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getOrderPriceUseCase()
            result.onSuccess {
                _orderPrice.value = it
            }.onFailure {
                Log.d(TAG, it.toString())
                _errorMessage.value = it.message ?: "Get order price failed"
            }
        }
        _isLoading.value = false
    }

    fun deletePartFromCart(partId: Long) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = deletePartFromCartUseCase(partId)
            result.onSuccess {
                calculateOrderPrice()
            }.onFailure {
                Log.d(TAG, it.toString())
                _errorMessage.value = it.message ?: "Delete order item failed"
            }
        }
        _isLoading.value = false
    }

    fun placeOrder() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val user = getUser()
                val address = getAddressByUserId(user.id)
                val orderRequest = formOrderRequest(address.id)
                processOrder(user.id, orderRequest)
            } catch (e: Exception) {
                handleFailure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    private suspend fun getUser(): User {
        return getUserUseCase().getOrElse {
            throw OrderProcessException("Get user failed", it)
        }
    }

    private suspend fun getAddressByUserId(userId: Long): Address {
        return getAddressByUserIdUseCase(userId).getOrElse {
            throw OrderProcessException("Get address by user id failed", it)
        }
    }

    private suspend fun formOrderRequest(addressId: Long): OrderRequest {
        return formOrderRequestUseCase(addressId).getOrElse {
            throw OrderProcessException("Form order request failed", it)
        }
    }

    private suspend fun processOrder(userId: Long, orderRequest: OrderRequest) {
        if (orderRequest.orderItems.isNotEmpty()) {
            createOrder(userId, orderRequest).onSuccess {
                _onSuccess.value = Unit
                emptyCart()
            }.onFailure {
                handleFailure(it, "Create order failed")
            }
        } else {
            _errorMessage.value = "Empty order"
        }
    }

    private suspend fun createOrder(userId: Long, orderRequest: OrderRequest): Result<Order> {
        return createOrderUseCase(userId, orderRequest)
    }

    private suspend fun emptyCart() {
        emptyCartUseCase().onSuccess {
            _orderItems.value = emptyList()
            _orderPrice.value = 0.0
        }
    }

    private fun handleFailure(throwable: Throwable, defaultMessage: String = "An error occurred") {
        Log.d(TAG, throwable.toString())
        _errorMessage.value = throwable.message ?: defaultMessage
    }

    private class OrderProcessException(message: String, cause: Throwable? = null) :
        RuntimeException(message, cause)

    companion object {
        private const val TAG = "CartVm"
    }
}